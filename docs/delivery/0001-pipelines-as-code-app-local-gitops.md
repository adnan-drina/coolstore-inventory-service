# Delivery Decision 0001: Pipelines-as-Code With App-Local GitOps

## Status

Accepted for the first delivery slice.

## Decision

Use Pipelines-as-Code under `.tekton/` for the first CI image-build path and keep OpenShift desired state under the application repository's `gitops/` directory.

The first slice uses:

- `.tekton/pull-request.yaml` as the pull-request PipelineRun;
- pull request events targeting `main`;
- Buildah with the app-local `Containerfile`;
- the OpenShift internal registry path `image-registry.openshift-image-registry.svc:5000/coolstore-inventory-dev/coolstore-inventory-service:<revision>`;
- app-local Kustomize state under `gitops/base` and `gitops/overlays/dev`;
- namespace `coolstore-inventory-dev`;
- app, service, route, runtime service account, and image repository name `coolstore-inventory-service`;
- the current in-memory Quarkus service only, with PostgreSQL deferred.

OpenShift Pipelines and Pipelines-as-Code are prerequisites for this slice. This repository does not install the operators and does not register an Argo CD application yet.

## Rationale

Pipelines-as-Code keeps the first delivery path close to the application source while making the generated pipeline reviewable in a pull request. App-local GitOps keeps the first demo simple and gives Developer Hub and documentation a concrete path to link to before a stricter environment-repository split is introduced.

The first pipeline deliberately stops after test, package, and image build. Deployment and promotion are documented through app-local GitOps and evidence files, but live reconciliation is deferred until the platform path is selected.

## Consequences

- The delivery packet is static and reviewable before a live PipelineRun exists.
- The OpenShift namespace must already have OpenShift Pipelines, Pipelines-as-Code, cluster-resolver tasks, image-push permissions, and a `Repository` custom resource before live validation.
- The first image tag is revision-based and mutable only as a review artifact; promotion must use an immutable digest once a real PipelineRun exists.
- PostgreSQL binding, rollout automation, SBOM generation, signing, provenance, scanning, and policy gates remain future delivery iterations.
