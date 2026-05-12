# Coolstore Inventory Service Repository Plan

## Purpose

This repository is the candidate service repository for the "From Vibe Coding to Agentic Engineering" demo.

The accepted first-demo shape is a single service repository:

- this repository should be renamed to `coolstore-inventory-service` after the direction is accepted;
- Quarkus source lives at the repository root;
- app-local GitOps desired state lives under `gitops/`;
- Tekton or OpenShift Pipelines assets live under `tekton/`;
- rollout, promotion, and rollback evidence lives in repository documentation.

## Current Repository Assessment

The current repository is a useful AI code-assistant workspace shell, but it is not yet the target application.

What exists now:

- OpenShift Dev Spaces `devfile.yaml`;
- Continue configuration template in `.vscode/config.yaml`;
- OpenCode configuration template in `.opencode/opencode.json`;
- Python starter exercises under `coding-exercises/game_starters/`;
- Python solution examples under `coding-exercises/game_solutions/`.

What exists after Item 2:

- Java and Quarkus project structure;
- Maven build;
- `coolstore-inventory-service` source code;
- deterministic Java tests;
- health and metrics extensions;
- project-local Maven repository settings for Red Hat build of Quarkus artifacts.

What does not exist yet:

- pipeline definitions;
- OpenShift or GitOps manifests.

What exists as planning placeholders after Item 1:

- `AGENTS.md`;
- `catalog-info.yaml`;
- `docs/analysis/item-1-app-repo-identity-analysis.md`;
- `docs/analysis/item-2-quarkus-service-scaffold-analysis.md`;
- `docs/analysis/item-3-enterprise-ai-controls-analysis.md`;
- `docs/analysis/item-4-developer-hub-metadata-analysis.md`;
- `docs/analysis/item-5-delivery-assets-analysis.md`;
- `docs/analysis/item-6-supply-chain-evidence-analysis.md`;
- `docs/evidence/README.md`;
- `docs/evidence/service-image-evidence.md`;
- `docs/evidence/promotion-decision.md`;
- `docs/tasks/continue-readme-api-test-plan-alignment.md`;
- `docs/tasks/opencode-reservation-endpoint.md`.

## Target Repository Responsibility

The renamed service repository should own:

- Quarkus source code and tests;
- service README and TechDocs-ready documentation;
- project-level AI instructions, such as `AGENTS.md`;
- Continue and OpenCode workspace configuration templates;
- Dev Spaces workspace definition;
- Developer Hub catalog metadata for the application component;
- local validation commands;
- app-local GitOps desired state under `gitops/`;
- Tekton or OpenShift Pipelines assets under `tekton/`;
- rollout, promotion, and rollback evidence;
- references to approved pipeline templates.

The service repository should not own:

- live secrets;
- manually edited cluster resources;
- production credentials;
- environment-specific data that belongs in a secured platform configuration source.

This shape keeps the first demo easy to run while preserving a reviewed delivery boundary between the platform repository and the service repository. A later iteration can split deployment state into another repository if the demo needs to teach stricter environment ownership.

## Target Application Shape

Service name:

```text
coolstore-inventory-service
```

Package:

```text
com.redhat.coolstore.inventory
```

Initial API:

```text
GET /api/inventory
GET /api/inventory/{itemId}
GET /api/inventory/{itemId}/availability
```

Possible agentic coding task:

```text
POST /api/inventory/{itemId}/reservations
```

The service should keep the first implementation bounded to inventory availability for Coolstore item IDs. Checkout, cart state, JMS order processing, frontend modernization, and full monolith conversion are outside the first implementation.

## Proposed Future Layout

```text
.
├── AGENTS.md
├── README.md
├── catalog-info.yaml
├── devfile.yaml
├── docs/
│   ├── architecture.md
│   ├── ai-assisted-development.md
│   └── coolstore-inventory-service-repository-plan.md
├── gitops/
│   ├── base/
│   └── overlays/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/redhat/coolstore/inventory/
│   │   └── resources/
│   └── test/
│       └── java/com/redhat/coolstore/inventory/
├── tekton/
├── .opencode/
│   └── opencode.json
└── .vscode/
    ├── config.yaml
    └── extensions.json
```

The existing Python exercise material should be archived under `legacy/python-exercises/` only after the Quarkus scaffold exists so it does not confuse Developer Hub, OpenCode, or pipeline discovery.

## Iteration Plan

### Iteration 1: Repository Decision

- Record this repository as the future `coolstore-inventory-service` repository.
- Record the single-repo first-demo layout.
- Keep changes documentation-only until the Quarkus service scaffold begins.

### Iteration 2: Source Repository Reshape

- Reframe the Python exercise README as the transition `coolstore-inventory-service` README.
- Add `AGENTS.md` as a transition guardrail before the final enterprise coding rules are implemented. Done in Item 1; strengthened in Item 3.
- Add `catalog-info.yaml` as preliminary Developer Hub metadata before Stage 100 and Stage 090 catalog wiring are finalized.
- Keep Continue and OpenCode configuration, but update the model instructions for Java and Quarkus work.
- Add the first Continue task for README, API, and test-plan alignment under `docs/tasks/`. Done in Item 1; read/write boundaries added in Item 3.
- Add the first OpenCode task for the bounded `POST /api/inventory/{itemId}/reservations` feature under `docs/tasks/`. Done in Item 1; read/write boundaries added in Item 3.
- Archive the current Python exercise files under `legacy/python-exercises/` only after the Quarkus scaffold exists.

### Iteration 2A: Enterprise AI Controls

- Expand `AGENTS.md` into repo-local project rules for the current Quarkus phase. Done in Item 3.
- Require analysis-first Continue work with documentation and test-plan write boundaries. Done in Item 3.
- Require plan-before-edit OpenCode work with explicit allowed source, test, and documentation paths. Done in Item 3.
- Configure OpenCode to load `AGENTS.md` and ask before write-like or external-action tools. Done in Item 3.
- Keep GitOps, Tekton, PostgreSQL, and legacy Python archive work out of the first OpenCode feature task. Done in Item 3.

### Iteration 3: Quarkus Service Scaffold

- Add Red Hat build of Quarkus `3.27.3.SP1-redhat-00002` with Java 21. Done in Item 2.
- Add inventory resource, in-memory repository, model records, and deterministic tests. Done in Item 2.
- Use test configuration that does not require Docker, PostgreSQL, OpenShift, or a live cluster. Done in Item 2.
- Add health and metrics extensions. Done in Item 2.
- Add PostgreSQL runtime configuration for the OpenShift Developer Catalog / Red Hat PostgreSQL image path in a later item.

### Iteration 3A: Developer Hub Metadata

- Refine `catalog-info.yaml` as the `coolstore-inventory-service` Component entity. Done in Item 4.
- Align the component owner with the Stage 090 `ai-modernization-team` group. Done in Item 4.
- Add source, README, repository plan, Continue task, and OpenCode task links. Done in Item 4.
- Add future placeholders for app-local GitOps, Tekton, and evidence links without creating those assets. Done in Item 4.
- Defer TechDocs build configuration until documentation structure is selected. Done in Item 4.

### Iteration 4: App-Local GitOps

- Do not create app-local GitOps or Tekton assets until the delivery template and OpenShift handoff path are selected. Confirmed in Item 5.
- Add app-local GitOps base and overlays under `gitops/`.
- Add the OpenShift GitOps or Argo CD application resource only when the live path is selected.
- Define image update, rollback, and promotion evidence.
- Keep live secrets and private environment data out of git.

### Iteration 5: Pipeline And Supply Chain

- Add or reference approved Tekton or OpenShift Pipelines templates.
- Run tests before image build.
- Add SBOM, signature, provenance, scan, and policy evidence hooks.
- Surface repository, pipeline, app-local GitOps, rollout, promotion, rollback, and runtime links through Developer Hub.

Item 6 added a documentation-only evidence model under `docs/evidence/`:

- the first evidence scope is the future `coolstore-inventory-service` application image;
- local Maven build and test commands are recorded as current pre-image evidence;
- image digest, SBOM, vulnerability scan, signature, provenance, policy gate, promotion, and rollback fields are explicitly pending;
- no generated SBOM, image build, signature, pipeline YAML, GitOps manifests, or policy engine configuration was added.

Item 5 delivery analysis deferred asset creation until these decisions are made:

- `tekton/` versus `.tekton/` and whether Pipelines as Code is in scope;
- approved template source;
- OpenShift deployment handoff path;
- placeholder names for namespace, image repository, service account, route, PostgreSQL binding, and secret references.

## Validation Targets

Initial static validation:

```bash
git status --short --branch
git diff --check
```

Future application validation:

```bash
./mvnw test
./mvnw package
mvn verify
curl -f http://localhost:8080/q/health
curl -f http://localhost:8080/api/inventory
```

Future app-local GitOps validation belongs under this repository's `gitops/` path.

## Open Decisions

- Which downstream Dev Spaces, Developer Hub, and README links must be updated after the GitHub repository rename?
- Which exact directory names should hold rollout notes, promotion notes, and rollback evidence?
- Which approved Tekton or OpenShift Pipelines template should seed `tekton/`?
- Which concrete OpenShift Developer Catalog / Red Hat PostgreSQL image parameters should the first live deployment use?
- Which supply-chain controls are advisory for the first demo, and which must block promotion?
- Which registry and scanner should provide the first immutable image digest and vulnerability evidence?
