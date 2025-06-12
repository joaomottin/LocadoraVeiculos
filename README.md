<!-- Badges -->
<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue.svg" alt="Java 17" />
  <img src="https://img.shields.io/badge/Status-Conclu%C3%ADdo-green.svg" alt="Status" />
</p>

# 🚗 LocadoraVeiculos

> Sistema de gerenciamento de locação de veículos desenvolvido em Java, seguindo arquitetura MVC em camadas (controller, model, DAL e view).

---

## 📋 Sumário

- [✨ Funcionalidades](#-funcionalidades)  
- [🛠️ Tecnologias](#️-tecnologias)  
- [🚀 Como Executar](#-como-executar)  
- [🤝 Contribuição](#-contribuição)  
---

## ✨ Funcionalidades

- 🔐 **Autenticação de Usuários**: login e logout com registro de eventos em log.  
- 📊 **CRUD completo** de veículos (Carros, Motos), clientes e locações.  
- 🗂️ **DAL (Data Access Layer)** para persistência em arquivo ou banco (SQLite).  
- 🎛️ **Menus interativos** em console para navegação intuitiva.  
- ✅ **Validações** em formulários de cadastro e operações de locação/devolução.  
- 🧹 **Log de atividades** para auditoria e rastreamento de ações.

---

## 🛠️ Tecnologias

- **Java 17**  
- **VS Code** (com extensão Java)  

---

## 👥 Colaboradores

| Avatar | Usuário        | Nome               |
|:------:|:---------------|:-------------------|
| ![joao](https://github.com/joaomottin.png?size=40) | [joaomottin](https://github.com/joaomottin) | João Pedro Mottin |
| ![iago](https://github.com/Iago-13.png?size=40)   | [Iago-13](https://github.com/Iago-13)       | Iago Mayer        |
| ![cadu](https://github.com/cadu-magaton.png?size=40) | [cadu-magaton](https://github.com/cadu-magaton) | Cadu Magaton      |
| ![adesc](https://github.com/Adescarpim.png?size=40) | [Adescarpim](https://github.com/Adescarpim)   | Adescarpim        |

---

## 🚀 Como Usar

Basta clonar o repositório e executar:

```bash
git clone https://github.com/joaomottin/LocadoraVeiculos.git
cd LocadoraVeiculos
javac -d bin src/App.java
java -cp bin App

