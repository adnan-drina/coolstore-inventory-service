# Coolstore Inventory Service

This repository is the application repository for `coolstore-inventory-service` in the "From Vibe Coding to Agentic Engineering" demo.

The original Python coding exercises from the RHOAI3 Private AI Code Assistant demo are archived under `legacy/python-exercises/` as legacy context. The active service source is the Quarkus project at the repository root.

## Repository Direction

This repository is the renamed application repository for `coolstore-inventory-service`.

The accepted first-demo shape is a single service repository. This repository owns Quarkus source, tests, Dev Spaces configuration, Continue/OpenCode configuration, Developer Hub metadata, service documentation, app-local GitOps state under `gitops/`, Pipelines-as-Code assets under `.tekton/`, and rollout, promotion, and rollback evidence.

See [Coolstore Inventory Service Repository Plan](docs/coolstore-inventory-service-repository-plan.md) for the proposed iteration path.

## Current Status

Planning artifacts and the first Quarkus scaffold now exist:

- [Item 1 app repo identity analysis](docs/analysis/item-1-app-repo-identity-analysis.md)
- [Project AI instructions](AGENTS.md)
- [Developer Hub catalog metadata](catalog-info.yaml)
- [Continue README/API/test-plan alignment task](docs/tasks/continue-readme-api-test-plan-alignment.md)
- [OpenCode reservation endpoint task](docs/tasks/opencode-reservation-endpoint.md)
- [Item 2 Quarkus scaffold analysis](docs/analysis/item-2-quarkus-service-scaffold-analysis.md)
- [Item 3 enterprise AI controls analysis](docs/analysis/item-3-enterprise-ai-controls-analysis.md)
- [Item 4 Developer Hub metadata analysis](docs/analysis/item-4-developer-hub-metadata-analysis.md)
- [Item 5 delivery assets analysis](docs/analysis/item-5-delivery-assets-analysis.md)
- [Item 6 supply-chain evidence analysis](docs/analysis/item-6-supply-chain-evidence-analysis.md)
- [Delivery decision: Pipelines-as-Code with app-local GitOps](docs/delivery/0001-pipelines-as-code-app-local-gitops.md)
- [Pipelines-as-Code setup notes](docs/delivery/pipelines-as-code-setup.md)
- [Supply-chain evidence model](docs/evidence/README.md)
- [Getting started for Stage 100](docs/getting-started.md)

The initial Quarkus source code, Maven build, app-local `gitops/` manifests, `.tekton/` Pipelines-as-Code pull-request PipelineRun, and `Containerfile` now exist. PostgreSQL runtime configuration, live PipelineRun evidence, and live GitOps registration are still deferred to later items.
Use [AGENTS.md](AGENTS.md) as the shared project rule file for AI-assisted work.
The root [catalog-info.yaml](catalog-info.yaml) now identifies this repository as the `coolstore-inventory-service` Developer Hub component. Its visible link card is intentionally limited to `Source Repo`, `Dev Spaces`, and `Getting Started`; detailed `.tekton/`, `gitops/`, delivery, task, and evidence paths stay in repository documentation.
The canonical workspace setup guide is published from the platform repository through Developer Hub TechDocs. Use the Developer Hub `Getting Started` link for the step-by-step MaaS, Continue, and OpenCode setup flow.
The committed Continue template is `.continue/config.yaml`. The workspace startup command copies it to `~/.continue/config.yaml`, where the developer adds the real MaaS route and API key. Keep real values out of Git.
The delivery asset analysis is complete, and the selected first delivery path is Pipelines-as-Code with app-local GitOps. The current demo cluster still needs OpenShift Pipelines/Pipelines-as-Code prerequisites before live PaC validation.
The supply-chain evidence model is now documented under `docs/evidence/`, but image digest, SBOM, scan, signature, provenance, policy gate, promotion, and rollback evidence remain pending until a real PipelineRun exists.

## Planned Service Contract

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

## Quarkus Quick Start

The initial service uses Red Hat build of Quarkus `3.27.3.SP1-redhat-00002` with Java 21.

Run tests:

```bash
./mvnw test
```

Package the application:

```bash
./mvnw package
```

Start dev mode:

```bash
./mvnw quarkus:dev
```

Check the initial API:

```bash
curl -f http://localhost:8080/api/inventory
curl -f http://localhost:8080/api/inventory/329299
curl -f http://localhost:8080/api/inventory/329299/availability
curl -f http://localhost:8080/q/health
```

This scaffold intentionally uses in-memory inventory data. The repository now has static pipeline and app-local GitOps assets, but do not claim PostgreSQL, live OpenShift deployment, live PipelineRun, or live GitOps reconciliation behavior until those later items are implemented and validated.

## Delivery Asset Quick Start

The first delivery slice is reviewable locally. It does not install OpenShift Pipelines, create a Pipelines-as-Code `Repository` custom resource, register an Argo CD application, or deploy to a cluster.

Build the Quarkus fast-jar output used by the `Containerfile`:

```bash
./mvnw -B test package
```

Render app-local GitOps state:

```bash
oc kustomize gitops/overlays/dev
```

The first pull-request pipeline is:

```text
.tekton/pull-request.yaml
```

It is triggered by pull requests to `main`, runs `./mvnw -B test package`, builds the image with Buildah, and pushes to the OpenShift internal registry repository:

```text
image-registry.openshift-image-registry.svc:5000/coolstore-inventory-dev/coolstore-inventory-service:<revision>
```

The pipeline intentionally does not deploy the service in this slice. See [Pipelines-as-Code setup notes](docs/delivery/pipelines-as-code-setup.md) for live cluster prerequisites.

## Legacy Exercise Archive

Current status: the original Python exercises have been archived as legacy context under `legacy/python-exercises/`.

Use these only as historical examples. The active demo path starts from the
Quarkus service root and the Stage 100 Developer Hub / Dev Spaces entry point.

## Legacy Python Exercises

Three legacy game exercises in `legacy/python-exercises/game_starters/`:

| Exercise | What to Do |
|----------|-----------|
| `rock_paper_scissors/` | Ask Continue to make it enterprise-grade |
| `simple_quiz/` | Follow the prompts — ask Continue to generate a quiz |
| `word_scramble/` | Follow the prompts — ask Continue to generate a word scramble |

Solutions are in `legacy/python-exercises/game_solutions/`.

## Getting Your MaaS Endpoint And API Key

1. Log in to the RHOAI Dashboard
2. Go to **GenAI Studio > AI asset endpoints**
3. Select your project > **Models as a service** tab
4. Click **View** on the Nemotron model — copy the endpoint URL
5. Click **Generate API token** — copy the token

## References

- [MaaS Code Assistant Quickstart](https://docs.redhat.com/en/learn/ai-quickstarts/rh-maas-code-assistant)
- [A guide to AI code assistants with Red Hat OpenShift Dev Spaces](https://developers.redhat.com/articles/2026/01/28/guide-ai-code-assistants-red-hat-openshift-dev-spaces)
- [OpenCode: Model-neutral AI coding assistant for OpenShift Dev Spaces](https://developers.redhat.com/articles/2026/04/22/opencode-model-neutral-ai-coding-assistant-openshift-dev-spaces)
- [Continue — Open-Source AI Code Assistant](https://www.continue.dev/)
- [RHOAI3 Coding Demo](https://github.com/adnan-drina/rhoai3-coding-demo)

## Using OpenCode

OpenCode is a model-neutral CLI tool installed automatically in the workspace. The first planned implementation task is documented in [OpenCode reservation endpoint task](docs/tasks/opencode-reservation-endpoint.md). It should run only after the Continue alignment pass has completed and the OpenCode plan has been reviewed.

Before using OpenCode for implementation, review [AGENTS.md](AGENTS.md) and the task packet. The repo-local OpenCode config points at `AGENTS.md` and requires approval for write-like tools.

```bash
# Launch OpenCode
opencode

# Connect to your MaaS model using /connect command
# Select "OpenAI Compatible" provider
# Enter your MaaS endpoint and API key
```

OpenCode will be used later for:

- Reviewing git diffs and commit history
- Analyzing project structure
- Planning bounded Java/Quarkus changes
- Implementing the reviewed reservation endpoint task

See [OpenCode documentation](https://github.com/anomalyco/opencode) for more details.
