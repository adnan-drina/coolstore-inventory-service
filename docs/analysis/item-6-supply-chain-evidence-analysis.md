# Item 6 Analysis: Supply-Chain Evidence

## Scope

Define the first evidence model for the `coolstore-inventory-service` image and promotion decision. This item was originally documentation-only because the delivery template, image build path, registry, GitOps handoff, and policy enforcement mechanism were not selected yet. The delivery path slice has now selected `.tekton/`, Buildah, the OpenShift internal registry, and app-local GitOps, while live PipelineRun evidence and policy controls remain pending.

## Sources Reviewed

- Local app repository:
  - `AGENTS.md`
  - `README.md`
  - `catalog-info.yaml`
  - `docs/coolstore-inventory-service-repository-plan.md`
  - `docs/analysis/item-5-delivery-assets-analysis.md`
- Platform planning repository:
  - `stages/155-red-hat-trusted-software-supply-chain/README.md`
  - `stages/150-governed-pipeline-and-deployment/README.md`
  - `stages/140-golden-path-quarkus-service/README.md`
- rh-brain reference material:
  - `raw/Using containers to bring software engineering rigor to AI workloads.md`
  - `raw/How Red Hat OpenShift AI simplifies trust and compliance.md`
  - `wiki/demos/rhoai3-coding-demo Developer Workflow Extension.md`
  - `wiki/demos/Trusted Enterprise AI Development Platform on OpenShift AI.md`
- Official Red Hat sources checked:
  - Red Hat Trusted Software Supply Chain product page
  - Red Hat Trusted Artifact Signer product page and 1.4 documentation landing page
  - Red Hat Trusted Profile Analyzer product page and SBOM scan guidance
  - Red Hat OpenShift Pipelines 1.20 securing guide, including Tekton Chains and SBOM visibility
  - Red Hat Quay product page
  - Red Hat Advanced Cluster Security image scanning documentation

## Findings

- Stage 155 defines the minimum demo evidence as SBOM, signature, provenance, scan result, policy decision, and human approval.
- Red Hat Trusted Software Supply Chain positions the trusted path as an integrated code, build, deploy, and monitor workflow with SBOMs, attestations, signing, registry controls, GitOps, and policy checks.
- Red Hat Trusted Artifact Signer provides the signing and provenance lane. It is the correct future control for image signatures and attestation verification, but this repository should not create signing configuration before the pipeline and keyless or key-backed signing path are selected.
- Red Hat Trusted Profile Analyzer provides the SBOM, VEX, CVE, vulnerability, and dependency analysis lane. The first repository artifact should reserve space for SBOM and vulnerability results without claiming they exist.
- Red Hat OpenShift Pipelines 1.20 documentation covers Tekton Chains, signed pipeline run badges, SBOM visibility, and RHACS-backed project vulnerability display. Those controls depend on pipeline configuration and cannot be implemented in this item.
- Red Hat Quay and Red Hat Advanced Cluster Security are expected future registry and image scanning controls. The first demo now uses the OpenShift internal registry as the sandbox image target, but the repository still does not have a live image digest or scan result.
- rh-brain AI workload notes extend the same evidence model to MCP servers, model containers, skills, and agent containers. For this item, only the service image is in scope; AI artifacts are listed as future extension points.

## Implementation Decision

Add docs-only evidence records under `docs/evidence/`:

- `README.md` to describe the evidence model and current status;
- `service-image-evidence.md` to capture build, image, SBOM, scan, signature, provenance, and policy evidence for the service image;
- `promotion-decision.md` to capture the first promotion decision, rollback reference, and human review.

No SBOM generation, signing configuration, scan result, policy engine configuration, live PipelineRun evidence, or live deployment evidence should be claimed until those artifacts exist.

## Risks

- Evidence templates can be mistaken for completed compliance evidence. Each pending control must state its current status clearly.
- Creating generated SBOMs or scans before an approved image build path exists would produce disposable evidence that does not prove the trusted delivery path.
- Signing and policy examples can accidentally encode security-sensitive assumptions, such as registry names, issuer configuration, service accounts, or secret references.
- Supply-chain claims must remain bounded to demo planning until a live pipeline produces auditable artifacts.

## Expected File Changes

- `docs/analysis/item-6-supply-chain-evidence-analysis.md`
- `docs/evidence/README.md`
- `docs/evidence/service-image-evidence.md`
- `docs/evidence/promotion-decision.md`
- `README.md`
- `docs/coolstore-inventory-service-repository-plan.md`
- `catalog-info.yaml`

The later delivery path slice created `.tekton/`, `gitops/`, and `Containerfile` assets. This evidence item still should not create generated SBOMs, scan results, signatures, provenance attestations, policy gates, or executable deployment evidence without a real PipelineRun.
