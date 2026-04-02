# Relatório de Refatoração: Sistema Oficina WEG

## 1. Análise do Cenário Original (O Monolito)

O sistema original, denominado "SistemaOficinaWegCompleto", apresentava uma arquitetura centralizada em uma única classe funcional, conhecida como God Class. Essa abordagem trazia diversos problemas críticos para a operação da oficina:

* **Rigidez e Fragilidade:** Toda a lógica de turmas, alunos, permissões e fluxo de ordens de serviço estava concentrada em um único bloco.
* **Dificuldade de Manutenção:** Qualquer alteração na regra de aprovação de uma OS exigia a modificação de múltiplos blocos de if/else, aumentando o risco de bugs.
* **Limitação de Dados:** O armazenamento era baseado em vetores de tamanho fixo e estático, o que limitava a escalabilidade do sistema.
* **Falta de Segurança:** O sistema dependia de verificações manuais de IDs em vetores para validar se um usuário era Professor ou Aluno, tornando a lógica de acesso vulnerável.

---

## 2. A Nova Arquitetura

A solução refatorada utiliza o framework Spring Boot e segue os padrões de arquitetura em camadas para garantir a organização do código fonte.

### Princípios SOLID Aplicados
* **Single Responsibility Principle (SRP):** As responsabilidades foram desmembradas em classes distintas como Usuario, OrdemServico e serviços específicos.
* **Open/Closed Principle (OCP):** O uso de Enums para Perfis e Status permite que o sistema seja estendido com novos comportamentos sem modificar o código existente.
* **Dependency Inversion Principle (DIP):** A utilização de Repositories (interfaces) isola a lógica de negócio do acesso direto aos dados.

### Resolução de Problemas de Segurança e Manutenção
* **Encapsulamento:** As regras de negócio inegociáveis, como a restrição de que apenas professores abrem e aprovam OS, agora são protegidas por lógica de serviço e encapsulamento de atributos.
* **Rastreabilidade:** A nova estrutura de dados garante o registro obrigatório de Equipamento, Defeito, Materiais e Laudo Técnico em cada Ordem de Serviço.
* **Escalabilidade:** A substituição de vetores fixos por coleções dinâmicas (List) e persistência via JPA permite que o sistema cresça conforme a necessidade da escola técnica.

---

## 3. Instruções para Execução

1. Certifique-se de ter o Java e o Maven instalados no ambiente.
2. Clone o repositório contendo o código organizado nos pacotes model, repository, service e controller.
3. Execute a aplicação através da classe principal do Spring Boot.
4. O sistema processará as requisições através dos controladores:
    * **UsuarioController:** Para gestão de perfis (Aluno, Professor, Coordenador).
    * **OrdemServicoController:** Para abertura e encerramento de ordens seguindo as hierarquias de acesso.