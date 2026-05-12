# Item 4 Analysis: Developer Hub Metadata

## Scope

Refine the app repository `catalog-info.yaml` so Red Hat Developer Hub can represent `coolstore-inventory-service` as the target service component for the developer workflow demo. This item did not add Developer Hub platform manifests, software templates, TechDocs build configuration, GitOps assets, or pipeline assets. The later delivery path slice now links the component to the static `.tekton/`, `gitops/`, delivery, and evidence paths.

## Sources Reviewed

- Local app repository:
  - `catalog-info.yaml`
  - `README.md`
  - `docs/coolstore-inventory-service-repository-plan.md`
  - `AGENTS.md`
- Platform planning and stage repository:
  - `stages/090-developer-portal-self-service/README.md`
  - `gitops/stages/090-developer-portal-self-service/base/catalog/all.yaml`
  - `gitops/stages/090-developer-portal-self-service/base/rhdh-instance/app-config-configmap.yaml`
  - `docs/developer-workflow/100-governed-developer-entry-point.md`
  - `docs/developer-workflow/140-golden-path-quarkus-service.md`
  - `docs/developer-workflow/coding-exercises-app-repo-plan.md`
- rh-brain reference material:
  - `wiki/demos/rhoai3-coding-demo Developer Workflow Extension.md`
  - `raw/rhoai3-coding-demo.cursor at main.md`
  - `raw/Red Hat Developer Lightspeed.md`
  - `raw/How Developer Hub and OpenShift AI work with OpenShift.md`
  - `raw/Designing Golden Paths.md`
- Official Red Hat sources checked:
  - Red Hat Developer Hub 1.9 documentation landing page
  - Red Hat Developer Hub 1.9 Software Catalog guidance
  - Red Hat Developer Hub 1.9 Software Templates guidance
  - Red Hat Developer Hub 1.9 TechDocs guidance

## Findings

- Stage 090 currently publishes an `ai-modernization-team` group and a `coolstore` component through platform-managed catalog content.
- Red Hat Developer Hub 1.9 stores Software Catalog metadata as YAML files alongside source code and supports Component, API, Resource, and other entity kinds.
- RHDH catalog registration expects a root `catalog-info.yaml` for a source repository component.
- RHDH Software Templates are the later golden-path entry point, but this item only prepares the component metadata for an already-started repository.
- TechDocs can be added later, but the current repository does not yet include a reviewed TechDocs build configuration.

## Implementation Decision

Keep a single `Component` entity for now and make it more explicit:

- use `coolstore-inventory-service` as the component name and title;
- align `spec.owner` with the Stage 090 `ai-modernization-team` group;
- keep `spec.system: coolstore` and `spec.lifecycle: experimental`;
- add source repository annotations for the current repo and the planned rename;
- keep `backstage.io/techdocs-ref: dir:.` as a future-facing docs hook, but do not claim TechDocs is built yet;
- add links to README, repository plan, Continue task, OpenCode task, source repo, and GitOps, Pipelines-as-Code, delivery, and evidence paths as they become available.

## Risks

- The planned `adnan-drina/coolstore-inventory-service` repository does not exist until the rename is performed, so the current repository remains the active source annotation.
- TechDocs should be validated in Developer Hub only after a reviewed `mkdocs.yml` or equivalent TechDocs configuration is added.
- Pipeline and GitOps links now point to static repository assets. Rollout, promotion, rollback, and live evidence links remain placeholders until a real PipelineRun and deployment exist.

## Expected File Changes

- `catalog-info.yaml`
- `README.md`
- `docs/coolstore-inventory-service-repository-plan.md`
- `docs/analysis/item-4-developer-hub-metadata-analysis.md`
