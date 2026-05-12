# Continue Task: README, API, And Test-Plan Alignment

## Purpose

Use Continue for the first "enterprise vibe coding" task after the Quarkus scaffold or first source slice exists.

The task is analysis-first: Continue should compare the README, planned API, implementation, and test plan before suggesting documentation or code changes.

## When To Run

Run this task after the repository contains at least:

- the Quarkus scaffold;
- the initial inventory API implementation or a source slice;
- a service README;
- at least one test or a documented test plan.

Do not run this against only the legacy Python exercises.

## Read Scope

Continue should inspect only repository-local evidence for this task:

- `README.md`
- `AGENTS.md`
- `pom.xml`
- `src/main/java/com/redhat/coolstore/inventory/`
- `src/test/java/com/redhat/coolstore/inventory/`
- `docs/coolstore-inventory-service-repository-plan.md`
- `docs/tasks/`

Do not use private cluster details, external tickets, or corporate standards unless the human explicitly provides them for this task.

## Write Boundary

This task is read-only by default.

If the human approves edits after the analysis:

- documentation edits may touch `README.md` and `docs/`;
- test-plan edits may touch `docs/` or focused test files under `src/test/java/com/redhat/coolstore/inventory/`;
- source edits under `src/main/java/` require a separate implementation task.

Do not edit `.mvn/`, `pom.xml`, `.opencode/`, `.vscode/`, legacy Python exercise directories, `gitops/`, or `tekton/` for this task.

## Preferred Model Path

Use the private MaaS model path for source-code context, such as the on-cluster Nemotron endpoint configured through Continue.

Approved external models can be compared only when the prompt content is allowed by policy.

## Prompt

```text
Review the README, API contract, implementation, and test plan for this service.

Do not edit files yet.

Return:
1. README claims that are supported by implementation or tests.
2. README claims that are missing, stale, or not yet implemented.
3. API behavior that is implemented but undocumented.
4. Test coverage gaps for the current API.
5. The smallest safe documentation or test change to make next.
6. Files that would be touched if the human approves edits.

Use only repository evidence. Do not invent deployment status, pipeline status, or PostgreSQL behavior that is not present in the repo.
```

## Expected Output

- A gap list before any edits.
- A proposed minimal change.
- The exact write boundary for that change.
- A validation command to run after the change.
- A note identifying the model path used.

## Acceptance Criteria

- No credentials or private cluster details are included.
- Documentation changes describe only implemented or tested behavior.
- Any proposed code change is small enough for human review.
- No source files are edited unless the human explicitly converts the review into an implementation task.
- The developer records the validation result before accepting the change.
