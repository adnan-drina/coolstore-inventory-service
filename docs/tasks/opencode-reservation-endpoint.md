# OpenCode Task: Reservation Endpoint

## Purpose

Use OpenCode for the first bounded agentic engineering task after the initial inventory API exists and the Continue alignment pass has completed.

The task adds a reviewed reservation endpoint without broadening the service beyond the inventory availability boundary.

## Target Endpoint

```text
POST /api/inventory/{itemId}/reservations
```

## Preconditions

- The Quarkus scaffold exists.
- The initial inventory GET APIs exist.
- Tests for the current inventory behavior pass.
- `AGENTS.md` has been reviewed for the current implementation phase.
- Continue has produced a README/API/test-plan alignment note.

## Read Scope

OpenCode should inspect:

- `AGENTS.md`
- `README.md`
- `pom.xml`
- `src/main/java/com/redhat/coolstore/inventory/`
- `src/test/java/com/redhat/coolstore/inventory/`
- `docs/coolstore-inventory-service-repository-plan.md`
- this task file

Do not use legacy Python exercise directories as service context.

## Write Boundary

OpenCode must produce a plan before editing.

After human approval, allowed writes are limited to:

- `src/main/java/com/redhat/coolstore/inventory/`
- `src/test/java/com/redhat/coolstore/inventory/`
- `README.md`
- `docs/tasks/opencode-reservation-endpoint.md`

Human approval is required before changing:

- `pom.xml`
- `.mvn/`
- `.opencode/`
- `.vscode/`
- `devfile.yaml`
- `catalog-info.yaml`
- any file outside the inventory package or task documentation

Disallowed for this task:

- creating `gitops/` or `tekton/`;
- adding PostgreSQL, messaging, checkout, cart, payment, frontend, or OpenShift deployment behavior;
- editing or moving legacy Python exercise directories;
- hard-coding private cluster details or credentials.

## Prompt

```text
Plan the implementation of POST /api/inventory/{itemId}/reservations.

Do not edit files until the plan is reviewed.

The endpoint must stay bounded to inventory availability. It must not introduce checkout, cart state, payment, JMS order processing, frontend modernization, or full Coolstore monolith conversion.

Return:
1. Files expected to change.
2. Request and response shape.
3. Validation and error cases.
4. Tests to add or update.
5. Documentation updates needed.
6. Any assumptions that require human approval.
7. Confirmation that the proposed edits stay inside the approved write boundary.
```

## Guardrails

- Keep edits in application source, tests, and service documentation unless explicitly approved.
- Do not add `gitops/`, `tekton/`, or cluster manifests for this task.
- Do not add external dependencies unless the reason is documented and reviewed.
- Do not hard-code credentials, inventory data that belongs in a secured store, or private cluster details.
- Do not claim PostgreSQL or OpenShift deployment behavior unless it exists in the repo.

## Validation

Run after approved implementation edits:

```bash
./mvnw test
./mvnw package
git diff --check
```

For documentation-only updates, run:

```bash
git diff --check
```

## Acceptance Criteria

- The plan is reviewed before edits.
- Tests are added or updated for reservation behavior.
- Existing inventory GET APIs continue to pass.
- README/API documentation reflects only implemented behavior.
- Validation commands and results are recorded in the task notes or pull request summary.
