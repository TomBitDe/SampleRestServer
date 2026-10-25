# Code Stability Improvements

## 1. Thread Safety (highest impact)
- [x] Replace `static ArrayList<Airline> memList` with a concurrent structure (`ConcurrentHashMap<String, Airline>` or `CopyOnWriteArrayList`) in `MessageResource`.
- [x] Make read path (`jsonairlineinfo`) consistent with the chosen collection.
- [x] Convert `RestServer` static mutable state (`host`, `port`, `baseURI`, `httpServer`, `running`) to instance fields.
- [x] Mark `running` `volatile` and synchronize `startServices()` / `stopServices()`.
- [x] Guard against `MainEntry.restServer == null` in `startWS()` / `stopWS()` (lazy init or null check).

## 2. Error Handling
- [ ] Stop swallowing `IOException` in `jsonWeighingRequest`; return proper `Response` (400/500).
- [ ] Use try-with-resources for the request `BufferedReader` in `jsonWeighingRequest`.
- [ ] Log full exception objects (`LOG.error(msg, ex)`) instead of only `getMessage()` in `RestServer.startServices()`.
- [ ] Fix NPE in `jsonDeleteAirlineInfo` filter (`code.equals(a.getCode())` or `Objects.equals`).

## 3. Validation & Security
- [ ] Validate `Airline` input (null/blank code/name) at method boundaries.
- [ ] Replace MD5 with `HmacSHA256` in `MD5Signature`.
- [ ] Use `MessageDigest.isEqual` for constant-time signature comparison.

## 4. Resources & Clients
- [ ] Set connect/read timeouts and ensure disconnect in `JsonObjectFromUrlUtil`.
- [ ] Throw a specific exception type instead of generic `RuntimeException`.
- [ ] Close REST `Client` instances in `MessageResourceTest` (`client.close()`).
- [ ] Derive base URI from `RestServer` instead of hardcoded `localhost:8080` in tests.

## 5. Test Coverage
- [ ] Add tests for null/empty input.
- [ ] Add tests for unknown airline (DELETE 404).
- [ ] Add tests for duplicate POST.
- [ ] Add tests for malformed JSON.
- [ ] Add concurrency test for simultaneous POST/DELETE.

## Review
1. Thread Safety implemented:
- `MessageResource`: `memList` → `ConcurrentHashMap<String, Airline> memMap`; POST uses `putIfAbsent`, DELETE uses atomic `remove`, GET reads `values()`.
- `RestServer`: static mutable state → instance fields; `running` is `volatile`; `startServices()`/`stopServices()` and host/port accessors synchronized.
- `MainEntry`: lazy, synchronized `getRestServer()`; `startWS()`/`stopWS()` null-safe.
- Verified: `mvn clean compile` under JDK 26 → BUILD SUCCESS (28 sources).

### Side effects investigated and reverted
- `pom.xml`: was accidentally downgraded (JDK 26 → 18/17/11/8, dependency versions) while troubleshooting; original pom already JDK-26-compliant. Restored from HEAD.
- `simpleclient/`: package was moved out as a workaround, then restored; no content changes remain (JSON-P uses `jakarta.json` in HEAD).
- `dependency-reduced-pom.xml`: stale generated artifact from a build against the temporary pom. Restored from HEAD; regenerated on `mvn package`.
- `logs/access.log`: tracked, empty legacy file (not used by `conf/log4j2.xml`); a pre-existing staged deletion was reverted and the file restored.
- 4 unrelated files showed only CRLF diffs from PowerShell `Set-Content`; restored from HEAD.
- Current `git status`: only `MainEntry.java`, `MessageResource.java`, `RestServer.java` modified + untracked `tasks/`.
