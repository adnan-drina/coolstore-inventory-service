# Item 5 Analysis: Delivery Assets

## Scope

Assess whether this branch is ready to add app-local delivery assets under `tekton/` and `gitops/`. The implementation plan requires a chosen Tekton/OpenShift Pipelines template and an approved OpenShift deployment handoff path before those assets are created.

## Sources Reviewed

- Local app repository:
  - `AGENTS.md`
  - `catalog-info.yaml`
  - `README.md`
  - `docs/coolstore-inventory-service-repository-plan.md`
- Platform planning repository:
  - `docs/developer-workflow/150-governed-pipeline-and-deployment.md`
  - `docs/developer-workflow/155-red-hat-trusted-software-supply-chain.md`
  - `docs/developer-workflow/140-golden-path-quarkus-service.md`
  - `docs/developer-workflow/coding-exercises-app-repo-plan.md`
  - `gitops/argocd/app-of-apps/*.yaml`
- rh-brain reference material:
  - `raw/Designing Golden Paths.md`
  - `raw/Navigating AI risk Building a trusted foundation with Red Hat.md`
  - `raw/Using containers to bring software engineering rigor to AI workloads.md`
  - `wiki/demos/rhoai3-coding-demo Developer Workflow Extension.md`
- Official Red Hat sources checked:
  - Red Hat OpenShift Pipelines 1.20 documentation landing page
  - Red Hat OpenShift Pipelines 1.20 Pipelines as Code documentation
  - Red Hat Developer Hub 1.9 Software Templates guidance

## Findings

- Stage 150 explicitly says delivery assets should be generated from an approved template or golden-path packet, not invented by an agent.
- Red Hat OpenShift Pipelines 1.20 supports Pipelines as Code, where pipeline definitions can live in the source repository and trigger on source events.
- The Pipelines as Code default convention is `.tekton/`, while the current app-repo decision names `tekton/` for app-local pipeline assets. That naming difference must be resolved before adding files.
- The platform repo uses GitOps through Argo CD Applications for executable stages, but app-local GitOps for this service has not selected a handoff mechanism, namespace, image update strategy, or rollback model.
- The app repo has no selected image registry, image name, OpenShift namespace, route name, service account, secret names, or database binding strategy yet.

## Implementation Decision

Do not create `tekton/`, `.tekton/`, or `gitops/` assets in this item.

The next implementation needs a short delivery decision record first:

- choose `tekton/` versus `.tekton/` and whether Pipelines as Code is in scope for the first demo;
- choose the template source, such as Red Hat Developer Hub software-template output, a project-local golden-path packet, or an approved OpenShift Pipelines example;
- choose the OpenShift deployment handoff path: app-local GitOps manifests, Quarkus OpenShift extension output, S2I/import-from-git, or another reviewed path;
- define placeholder names for namespace, image repository, service account, route, PostgreSQL binding, and secret references;
- define static validation commands before live cluster execution.

## Risks

- Creating pipeline or GitOps YAML before these decisions would likely encode assumptions that the demo then has to undo.
- A `.tekton/` Pipelines as Code path and a `tekton/` repository path have different operational meanings.
- Delivery assets will become security-sensitive as soon as they reference registries, credentials, namespaces, or deployment authority.

## Expected File Changes

- `docs/analysis/item-5-delivery-assets-analysis.md`
- `README.md`
- `docs/coolstore-inventory-service-repository-plan.md`

No delivery asset directories should be created by this item.
