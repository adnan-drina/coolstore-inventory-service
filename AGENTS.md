# AGENTS.md

## Repository Identity

This repository is the `coolstore-inventory-service` repository for the "From Vibe Coding to Agentic Engineering" demo.

Treat this repository as the active Quarkus target-service repository:

- keep the archived Python exercises under `legacy/python-exercises/`;
- do not move, edit, or restore legacy Python exercise files unless the archive item explicitly starts;
- keep delivery assets bounded to `.tekton/`, `Containerfile`, `gitops/`, and `docs/delivery/` until the next delivery item starts;
- keep the current Quarkus scaffold bounded to inventory read APIs until the reservation endpoint task starts;
- do not commit secrets, model API keys, kubeconfigs, tokens, or private cluster credentials.

## Project Map

Important paths:

- `README.md` - service identity, local run instructions, and demo task links.
- `pom.xml` - Red Hat build of Quarkus project definition.
- `.mvn/` - project-local Maven configuration, including the Red Hat Maven repository.
- `src/main/java/com/redhat/coolstore/inventory/` - Quarkus inventory source.
- `src/test/java/com/redhat/coolstore/inventory/` - Quarkus inventory tests.
- `docs/coolstore-inventory-service-repository-plan.md` - staged implementation plan.
- `docs/delivery/` - delivery decision record and Pipelines-as-Code setup notes.
- `Containerfile` - Buildah image build input for the Quarkus fast-jar output.
- `.tekton/` - Pipelines-as-Code pull-request PipelineRun.
- `gitops/` - app-local Kustomize desired state for the first dev deployment.
- `docs/tasks/` - bounded Continue and OpenCode task packets.
- `.continue/config.yaml` - Continue configuration template.
- `.opencode/opencode.json` - OpenCode configuration template.
- `legacy/python-exercises/` - archived Python exercise material retained as legacy context.

## Service Contract

Service name:

```text
coolstore-inventory-service
```

Java package:

```text
com.redhat.coolstore.inventory
```

Initial API:

```text
GET /api/inventory
GET /api/inventory/{itemId}
GET /api/inventory/{itemId}/availability
```

First OpenCode feature task:

```text
POST /api/inventory/{itemId}/reservations
```

## AI-Assisted Work Rules

- Use Continue first for README, API, and test-plan alignment.
- Use OpenCode for bounded engineering tasks after a reviewed plan exists.
- Keep source-code tasks on the private MaaS model path unless policy explicitly permits another path.
- Ask for a plan before large edits.
- Keep generated changes small enough for human review.
- Update documentation only for behavior that exists in code or tests.
- Record validation commands and results with each meaningful change.
- Disclose material AI assistance in pull request summaries.

## Source And Model Boundaries

- Treat source code, tests, and unpublished design notes as private project context.
- Prefer the MaaS-routed on-cluster model for source-code work.
- Use approved external models only for content that policy allows to leave the private model path.
- Do not paste secrets, API keys, tokens, kubeconfigs, cluster passwords, or private route credentials into prompts.
- Keep MaaS route and API key values as placeholders in committed configuration.
- Do not fetch or cite corporate standards unless they are present in the repository, approved MCP context, or explicitly provided by the human.

## Path And Write Boundaries

For all AI-assisted work:

- Read freely from `README.md`, `AGENTS.md`, `pom.xml`, `.mvn/`, `src/`, `docs/`, `.vscode/`, `.opencode/`, and `devfile.yaml` when relevant to the task.
- Do not edit generated build output under `target/`.
- Do not edit or move legacy Python exercise directories unless the archive item explicitly starts.
- Do not create additional live deployment, rollout, or promotion assets outside `.tekton/`, `Containerfile`, `gitops/`, and `docs/delivery/` unless the next delivery item explicitly starts.
- Do not change `.mvn/`, `pom.xml`, or dependency versions unless the task explicitly requires a build or dependency decision.
- Do not add external services, database runtime dependencies, container build assets, or OpenShift manifests unless the current item calls for them.
- Keep documentation claims tied to implemented code, tests, or clearly marked future plans.

Task-specific write boundaries:

- Continue alignment task: read-first; approved writes may touch `README.md`, `docs/`, and focused tests only.
- OpenCode reservation endpoint task: approved writes may touch `src/main/java/com/redhat/coolstore/inventory/`, `src/test/java/com/redhat/coolstore/inventory/`, `README.md`, and `docs/tasks/opencode-reservation-endpoint.md`.
- Developer Hub metadata task: approved writes may touch `catalog-info.yaml` and docs only.
- Delivery tasks: approved writes may touch `.tekton/`, `Containerfile`, `gitops/`, `docs/delivery/`, `docs/evidence/`, `README.md`, `AGENTS.md`, and `catalog-info.yaml` after their analysis gates complete.

## Required Workflow

For non-trivial AI-assisted changes:

1. Read `AGENTS.md`, the relevant task packet under `docs/tasks/`, and the affected source or docs.
2. Produce a short plan before editing.
3. List expected files to change.
4. Keep edits within the current task boundary.
5. Run the narrowest validation command.
6. Summarize changed files, validation, residual risk, and AI assistance.

## Current Validation Posture

Static validation:

```bash
git diff --check
```

Quarkus validation:

```bash
./mvnw test
./mvnw package
```

Delivery validation:

```bash
oc kustomize gitops/overlays/dev
```

Run `tkn pac resolve .tekton/pull-request.yaml` only when OpenShift Pipelines, Pipelines-as-Code, and `tkn` are installed.
