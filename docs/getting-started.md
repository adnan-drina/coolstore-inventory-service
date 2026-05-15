# Getting Started

This page is the app-local companion for the `coolstore-inventory-service`
Stage 100 entry point. The canonical step-by-step workspace setup guide is
published from `rhoai3-coding-demo` through Developer Hub TechDocs.

## Start From Developer Hub

Open the `Coolstore Inventory Service` catalog component in Red Hat Developer
Hub. The component link card should stay intentionally small:

- `Source Repo` opens the current source repository.
- `Dev Spaces` opens the governed workspace path for this demo environment.
- `Getting Started` opens the Developer Hub TechDocs workspace guide.

Detailed planning, delivery, task, and evidence documents remain in this
repository, but they should not crowd the first Developer Hub component view.
Use this app-local page after the TechDocs guide has opened the workspace.

## Open The Workspace

Use the Developer Hub `Dev Spaces` link for live demos. The committed catalog
metadata uses a placeholder because the real Dev Spaces route is specific to
the sandbox cluster and must not be committed.

Red Hat OpenShift Dev Spaces supports starting a workspace from a Git repository
URL, and this repository keeps `devfile.yaml` at the root for that path. The
Stage 100 platform catalog generates the live Dev Spaces URL at sync time.

## Select The Model Path

Use the private MaaS model path for source-code work:

```text
nemotron-3-nano-30b-a3b
```

Keep MaaS routes, API keys, tokens, kubeconfigs, and cluster credentials out of
git. Committed Continue and OpenCode configuration must keep placeholders only.

## Validate The Starting Point

Before moving to Stage 110, confirm:

- Developer Hub shows the `Coolstore Inventory Service` component.
- The component exposes only `Source Repo`, `Dev Spaces`, and `Getting Started`.
- `Getting Started` opens the TechDocs workspace guide.
- The Dev Spaces workspace opens.
- The workspace contains `mca-coolstore` and `coolstore-inventory-service`.
- `./mvnw test` passes in this repository.
- The private MaaS model path is selected for source-code context.
- Continue and OpenCode both pass a harmless MaaS verification prompt.

## Next Task

Stage 110 uses Continue for README, API, and test-plan alignment. Start with
[`docs/tasks/continue-readme-api-test-plan-alignment.md`](tasks/continue-readme-api-test-plan-alignment.md)
after the Stage 100 checks are green.
