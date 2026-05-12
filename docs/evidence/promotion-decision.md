# Promotion Decision

Decision target:

```text
coolstore-inventory-service application image
```

Current status: deferred. Static app-local GitOps state and a Pipelines-as-Code image-build path exist, but no live service image digest, rollout, or deployment policy gate exists yet.

## Candidate Artifact

| Field | Value |
| --- | --- |
| Source commit | Pending |
| Pipeline run | Pending |
| Image digest | Pending |
| SBOM reference | Pending |
| Scan reference | Pending |
| Signature reference | Pending |
| Provenance reference | Pending |

## Review Checklist

| Check | Decision | Evidence |
| --- | --- | --- |
| Source change reviewed by a human | Pending | Pending |
| Tests passed | Pending | Pending |
| Package/build completed | Pending | Pending |
| SBOM exists and is readable | Pending | Pending |
| Vulnerability findings reviewed | Pending | Pending |
| Image digest captured | Pending | Pending |
| Image signature verified | Pending | Pending |
| Provenance verified | Pending | Pending |
| Policy gate result reviewed | Pending | Pending |
| GitOps target revision selected | Pending | `gitops/overlays/dev` exists; no live registration yet |
| Rollback path identified | Pending | Pending |

## Decision

| Field | Value |
| --- | --- |
| Promotion decision | Deferred |
| Target environment | `coolstore-inventory-dev`, pending live validation |
| Decision owner | Pending |
| Date | Pending |
| Conditions or exceptions | Pending |
| Rollback reference | Pending |

## Rollback Record

| Field | Value |
| --- | --- |
| Previous approved digest | Pending |
| Previous GitOps revision | Pending |
| Rollback command or runbook | Pending |
| Verification command | Pending |

## Human Approval

Record the human approval note here after the first complete evidence bundle exists.

```text
Pending.
```
