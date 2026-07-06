---
name: quarkus-rest-endpoint
description: >-
  How this project builds Quarkus REST endpoints. Use whenever adding or
  changing a JAX-RS resource, path, or HTTP operation in
  coolstore-inventory-service — before writing any resource code.
---

# Quarkus REST Endpoint Standards

These are the binding conventions for REST resources in this repository.
They exist so every endpoint looks like it was written by the same team.
Follow them exactly; when a task genuinely requires deviating, say so in the
task summary and wait for review agreement instead of silently diverging.

## Resource class conventions

- One resource class per domain area, named `<Domain>Resource`, in
  `src/main/java/com/redhat/coolstore/inventory/`.
- Class-level `@Path("/api/<domain>")` — all public paths live under `/api/`.
- Class-level `@Produces(MediaType.APPLICATION_JSON)`; add `@Consumes`
  only on operations that accept a body.
- Use `jakarta.ws.rs` imports (never `javax.ws.rs`) and constructor-free
  field injection with `@Inject` for collaborators, matching
  `InventoryResource`.

## Operation conventions

- Path parameters use the domain identifier name (`{itemId}`), not `{id}`.
- Return domain records directly; JSON-B serializes them. Do not wrap
  responses in `Response` unless setting a non-200 status or headers.
- Missing entities throw `jakarta.ws.rs.NotFoundException` with a message
  in the form `"No <thing> found for <key> <value>"`. Share a private
  `require<Thing>(...)` helper when more than one operation needs it.
- Validation failures on request input throw `BadRequestException` with a
  one-line reason. No custom exception mappers without a review decision.
- New mutating operations (`@POST`, `@PUT`, `@DELETE`) return the affected
  record (200) or `Response.status(201).entity(...)` on creation.

## What NOT to do

- No business logic in resource classes — delegate to the repository or a
  service class; resources translate HTTP to domain calls only.
- No new top-level path prefixes: everything stays under `/api/`.
- No DTO duplication of records that already exist; extend the record or
  add a new one in the domain model (see `inventory-domain-model` skill).
- No changes to existing public paths or response shapes without an
  explicit task instruction — clients and the README API table depend on
  them.

## Definition of done for an endpoint change

1. Resource follows the conventions above.
2. Tests added per the `project-test-standards` skill.
3. README API table and test plan updated per the `api-docs-consistency`
   skill.
4. `./mvnw test` passes locally.
