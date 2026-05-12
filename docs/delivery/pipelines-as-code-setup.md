# Pipelines-as-Code Setup Notes

## Current Validation Posture

The delivery assets in this repository are static review artifacts until the target OpenShift cluster has OpenShift Pipelines and Pipelines-as-Code available.

The current demo cluster context did not show Tekton, Pipelines-as-Code, PipelineRun, or `Repository` API resources when this delivery path was selected. Live validation therefore requires cluster preparation outside this application branch.

## Required Cluster Prerequisites

Before running `.tekton/pull-request.yaml` live:

1. Install or confirm Red Hat OpenShift Pipelines 1.20.
2. Confirm Pipelines-as-Code is enabled.
3. Create or confirm namespace `coolstore-inventory-dev`.
4. Create a Pipelines-as-Code `Repository` custom resource for this GitHub repository in `coolstore-inventory-dev`.
5. Confirm the cluster resolver can resolve `git-clone` and `buildah` tasks from namespace `openshift-pipelines`.
6. Confirm the PipelineRun service account can create PipelineRuns, use workspaces, run Buildah, and push to:

   ```text
   image-registry.openshift-image-registry.svc:5000/coolstore-inventory-dev/coolstore-inventory-service
   ```

7. Confirm the internal registry route or service is available to the build task.

## Local Static Checks

Run these checks before any live PipelineRun:

```bash
./mvnw -B test
./mvnw -B package
git diff --check
oc kustomize gitops/overlays/dev
```

Parse the PaC YAML with an available YAML parser. When `tkn` and Pipelines-as-Code are installed, run the PaC resolver check as an additional validation step:

```bash
tkn pac resolve .tekton/pull-request.yaml
```

## Non-Goals In This Slice

- Do not install OpenShift Pipelines from this repository.
- Do not create an Argo CD application from this repository yet.
- Do not deploy directly from the PipelineRun.
- Do not add PostgreSQL runtime dependencies yet.
- Do not claim SBOM, scan, signature, provenance, or policy-gate evidence until a real PipelineRun generates those artifacts.
