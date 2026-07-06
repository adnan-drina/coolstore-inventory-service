---
name: inventory-domain-model
description: >-
  How this project models domain data and repositories. Use whenever adding
  or changing domain records, repository methods, or seed data in
  coolstore-inventory-service.
---

# Domain Model and Repository Standards

This project deliberately uses plain Java records plus an injectable
repository — not Panache, not JPA entities — so the demo stays focused on
API and workflow behavior. Do not introduce a persistence framework unless
the task explicitly starts that work.

## Records

- Domain types are Java records in
  `src/main/java/com/redhat/coolstore/inventory/`, one file per record,
  named as the business concept (`InventoryItem`, `InventoryAvailability`).
- Record components use domain identifier names (`itemId`, not `id`) and
  primitive/simple types. Derived values (like availability) are computed
  into their own record, not added as extra fields on the source record.
- Records are immutable snapshots: no setters, no mutable collections.

## Repositories

- One repository class per aggregate (`InventoryRepository`), annotated
  `@ApplicationScoped`, injected into resources with `@Inject`.
- Lookup methods return `Optional<T>` (`findByItemId`); list methods return
  `List<T>` copies, never internal mutable state.
- Seed/demo data lives inside the repository initialization so tests and
  the demo have deterministic content. Keep the three canonical items
  (`329299`, `329199`, `165613`) stable — tests and demo scripts rely on
  them; add new seed items rather than changing existing ones.

## Changing the model

- Additive changes (new record, new repository method) are the norm.
- Renaming or removing record components is a breaking API change: it
  requires an explicit task instruction and updates to the README API table,
  tests, and any consumer noted there.
- Concurrency: mutating repository state (reservations, quantity updates)
  must keep the repository thread-safe; prefer immutable snapshots plus
  atomic replacement over in-place mutation.
