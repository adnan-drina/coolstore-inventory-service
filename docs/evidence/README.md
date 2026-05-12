# Supply-Chain Evidence

This directory defines the first evidence model for the planned `coolstore-inventory-service` image.

Current status: documentation-only. The service has a Quarkus scaffold and local Maven validation, but it does not yet have a selected image build pipeline, image registry, SBOM generator, scanner, signing path, provenance attestation, GitOps handoff, or deployment policy gate.

## Evidence Scope

Initial artifact in scope:

```text
coolstore-inventory-service application image
```

Future artifacts that may use the same pattern:

- custom MCP server images;
- packaged agent or skill artifacts;
- model containers or OCI artifacts;
- workspace images if the demo adopts a shared custom Dev Spaces image.

## Minimum Evidence Bundle

The first trusted service-image bundle should capture:

| Evidence | Current status | Planned control |
| --- | --- | --- |
| Source revision | Pending commit selection | Git commit SHA and branch |
| Build result | Local Maven package only | OpenShift Pipelines or approved Tekton task |
| Image reference and digest | Not created | Approved registry, likely Quay or OpenShift image registry |
| SBOM | Not generated | Pipeline-generated CycloneDX or SPDX document |
| Vulnerability scan | Not generated | Red Hat Trusted Profile Analyzer, Red Hat Quay, or Red Hat Advanced Cluster Security |
| Signature | Not generated | Red Hat Trusted Artifact Signer or approved Sigstore-compatible path |
| Provenance | Not generated | Tekton Chains, in-toto, SLSA-aligned attestation, or enterprise contract path |
| Policy decision | Deferred | Advisory or blocking gate selected by the delivery design |
| Promotion decision | Deferred | Human-reviewed promotion record |
| Rollback reference | Deferred | Previous approved digest and app-local GitOps revision |

## Files

- [Service image evidence](service-image-evidence.md) records artifact evidence for the service image.
- [Promotion decision](promotion-decision.md) records the human promotion decision and rollback reference.

## Guardrails

- Do not commit registry credentials, signing keys, tokens, kubeconfigs, or scan credentials.
- Do not mark a control as complete unless the referenced artifact exists and can be independently verified.
- Record placeholders as `Pending`, `Deferred`, or `Not applicable` rather than leaving them ambiguous.
- Keep evidence tied to immutable identifiers such as git SHAs, image digests, SBOM checksums, and pipeline run IDs.
- Treat AI-generated supply-chain changes as drafts until a human reviews the full diff and validation output.

## References

- Red Hat Trusted Software Supply Chain
- Red Hat Trusted Artifact Signer
- Red Hat Trusted Profile Analyzer
- Red Hat OpenShift Pipelines and Tekton Chains
- Red Hat Quay
- Red Hat Advanced Cluster Security for Kubernetes
