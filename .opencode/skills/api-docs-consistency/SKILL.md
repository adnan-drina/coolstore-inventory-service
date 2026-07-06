---
name: api-docs-consistency
description: >-
  Keep README API documentation, the test plan, and the implemented
  endpoints aligned. Use at the end of every task that adds or changes an
  endpoint, record shape, or seed data in coolstore-inventory-service.
---

# API Documentation Consistency

The README is the service contract the demo audience reads. Code, tests,
and README must tell the same story at the end of every task — drift
between them is treated as a defect, exactly like a failing test.

## What must stay aligned

1. **README API table** — every public endpoint appears with method, path,
   parameters, response shape, and error behavior. New endpoints add a
   row; changed endpoints update the row in the same change.
2. **README examples** — sample `curl` calls and sample JSON reflect the
   real record shapes and canonical seed data. If a record gains a
   component, the sample JSON shows it.
3. **Test plan** — the documented test expectations (see
   `docs/tasks/` packets and README testing section) list the behaviors
   the test suite actually asserts. New coverage per
   `project-test-standards` gets a matching line.

## Working rules

- Read the README API section BEFORE implementing an endpoint change; if
  the README already documents the intended behavior, implement to the
  documentation, not to your assumption.
- If implementation forces a contract change (path, field name, status
  code), stop and surface it for review before updating the docs — the
  documentation is the reviewed artifact, code follows it.
- Keep wording factual and terse in the API table; explanation belongs in
  prose sections, not table cells.
- Never document endpoints that do not exist yet ("coming soon" rows are
  not allowed); the backlog lives in `docs/tasks/`, not the contract.

## Definition of done

- README API table, examples, and test plan updated in the same commit as
  the code change.
- A reviewer can verify the change by reading the README diff next to the
  code diff and finding no contradiction.
