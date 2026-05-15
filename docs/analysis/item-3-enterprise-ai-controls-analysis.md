# Item 3 Analysis: Enterprise AI Controls

## Scope

Turn the transition guardrails from Items 1 and 2 into concrete repo-local controls for the first Continue and OpenCode tasks. This item does not implement the reservation endpoint and does not add GitOps, Tekton, PostgreSQL, or cluster deployment assets.

## Sources Reviewed

- Local app repository:
  - `AGENTS.md`
  - `README.md`
  - `.vscode/config.yaml`
  - `.opencode/opencode.json`
  - `devfile.yaml`
  - `docs/tasks/continue-readme-api-test-plan-alignment.md`
  - `docs/tasks/opencode-reservation-endpoint.md`
- Platform planning repository:
  - `AGENTS.md`
  - `docs/AI_COLLABORATION.md`
  - `docs/DEVELOPER_WORKSPACE_GUIDE.md`
  - `stages/130-agentic-engineering-with-opencode/README.md`
  - `stages/140-golden-path-quarkus-service/README.md`
  - `stages/140-golden-path-quarkus-service/quarkus-target-service-options.md`
- rh-brain reference material:
  - `wiki/demos/rhoai3-coding-demo Developer Workflow Extension.md`
  - `raw/OpenCode A model-neutral AI coding assistant for OpenShift Dev Spaces.md`
  - `raw/A guide to AI code assistants with Red Hat OpenShift Dev Spaces.md`
  - `raw/Rules.md`
  - `raw/Tools.md`
- Official Red Hat sources checked:
  - Red Hat OpenShift Dev Spaces 3.24 user guide
  - OpenCode: A model-neutral AI coding assistant for OpenShift Dev Spaces
  - A guide to AI code assistants with Red Hat OpenShift Dev Spaces

## Findings

- The app repo already has a Dev Spaces workspace, Continue config template, OpenCode config template, and the initial Quarkus scaffold.
- Red Hat Dev Spaces guidance supports a repository devfile as the workspace entry point and describes the workspace as the controlled place where developer tooling runs.
- Red Hat AI code-assistant guidance positions on-prem OpenShift AI model endpoints as the private path for sensitive source code and recognizes Continue as a Dev Spaces-compatible, open source assistant.
- The Red Hat OpenCode article supports OpenCode as a terminal-based, model-neutral assistant inside Dev Spaces.
- The platform Stage 130 planning docs require plan-before-edit behavior, scoped permissions, evidence capture, and human review.
- The local OpenCode docs in rh-brain show that `AGENTS.md` is the project instruction file and that OpenCode permissions can require approval for edit, write, bash, web, and MCP tools.

## Implementation Decision

Strengthen the repository controls in place:

- expand `AGENTS.md` from a transition note into the current project rules for AI-assisted work;
- make the Continue task explicitly read-only by default and limit any approved writes to docs and tests;
- make the OpenCode reservation task explicit about allowed read paths, allowed write paths, dependency boundaries, and validation;
- update `.opencode/opencode.json` with a schema, `AGENTS.md` instruction reference, and approval-required permissions for write-like or external-action tools;
- update the README and repository plan to point developers at the strengthened controls.

## Risks

- OpenCode permission keys should be revalidated against the exact OpenCode version used in Dev Spaces before turning this into a hard demo claim.
- Task packets are governance aids, not enforcement by themselves; human review and OpenCode permissions still need to be used during the demo.
- The existing config files still contain placeholders for MaaS route and API key; no real credentials should be committed.
- The app repo still has legacy Python exercises, so path boundaries must keep Quarkus tasks out of those directories.

## Expected File Changes

- `AGENTS.md`
- `.opencode/opencode.json`
- `README.md`
- `docs/coolstore-inventory-service-repository-plan.md`
- `docs/tasks/continue-readme-api-test-plan-alignment.md`
- `docs/tasks/opencode-reservation-endpoint.md`
- `docs/analysis/item-3-enterprise-ai-controls-analysis.md`
