# Item 1 Analysis: App Repo Identity

## Scope

Prepare this repository as the future `coolstore-inventory-service` application repository without adding Quarkus source, Tekton assets, GitOps manifests, or moving the existing Python exercises.

## Sources Reviewed

- Local app repository:
  - `README.md`
  - `docs/coolstore-inventory-service-repository-plan.md`
  - `devfile.yaml`
  - `.vscode/config.yaml`
  - `.opencode/opencode.json`
- Platform planning repository:
  - `docs/developer-workflow/coding-exercises-app-repo-plan.md`
  - `docs/developer-workflow/100-governed-developer-entry-point.md`
  - `docs/developer-workflow/110-enterprise-vibe-coding-with-continue.md`
  - `docs/developer-workflow/130-agentic-engineering-with-opencode.md`
- rh-brain reference material:
  - `wiki/demos/rhoai3-coding-demo Developer Workflow Extension.md`
  - `raw/OpenCode A model-neutral AI coding assistant for OpenShift Dev Spaces.md`
  - `raw/Red Hat OpenShift Dev Spaces - A consistent, more secure collaborative development environment.md`
- Official Red Hat sources checked:
  - Red Hat OpenShift Dev Spaces 3.24 user guide
  - OpenCode: A model-neutral AI coding assistant for OpenShift Dev Spaces
  - A guide to AI code assistants with Red Hat OpenShift Dev Spaces

## Findings

- The repository already has the right workspace shell for the first demo: `devfile.yaml`, Continue configuration, and OpenCode configuration.
- The current visible README still leads with Python coding exercises, which is useful legacy context but not the future service identity.
- No Java, Quarkus, Maven, Developer Hub catalog metadata, service instructions, or task packets exist yet.
- Dev Spaces documentation confirms that a repository-level `devfile.yaml` is a supported workspace entry point, so the existing devfile should be preserved until the repository rename and Quarkus scaffold require concrete changes.
- Red Hat AI assistant guidance supports the planned shape: a reproducible Dev Spaces workspace, governed model endpoint configuration, and open source assistant tooling.
- The OpenCode article supports keeping OpenCode available as a terminal-based, model-neutral assistant in Dev Spaces, but the repo still needs project instructions before agentic work is useful.

## Implementation Decision

For Item 1, add identity and task placeholders only:

- reframe the README around the planned `coolstore-inventory-service`;
- keep the Python exercise instructions as legacy material;
- add a placeholder `AGENTS.md` that tells future agents this repo is still in transition;
- add a minimal placeholder `catalog-info.yaml` for future Developer Hub refinement;
- add task packets for the first Continue and OpenCode demo tasks;
- update the repository plan to list these new placeholders.

## Risks

- `catalog-info.yaml` is intentionally preliminary and must be reviewed again in the Developer Hub metadata item.
- `AGENTS.md` is a transition guardrail, not the final enterprise AI controls implementation.
- The repository directory and GitHub repository may still be named `coding-exercises`, so devfile path changes are deferred.
- The Python exercises remain in place and could still confuse discovery tools until they are archived after the Quarkus scaffold exists.

## Expected File Changes

- `README.md`
- `AGENTS.md`
- `catalog-info.yaml`
- `docs/coolstore-inventory-service-repository-plan.md`
- `docs/analysis/item-1-app-repo-identity-analysis.md`
- `docs/tasks/continue-readme-api-test-plan-alignment.md`
- `docs/tasks/opencode-reservation-endpoint.md`
