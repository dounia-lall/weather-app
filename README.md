# 🌦️ Weather App - Pipeline Météo Temps Réel

## 📌 Description

Ce projet est une application de streaming de données météo en temps réel développée avec des technologies Big Data et Cloud modernes.

L’application récupère des données météo en direct via l’API OpenWeather, les transmet dans Apache Kafka, les traite avec Spring Boot, les indexe dans Elasticsearch puis les visualise dans des dashboards Kibana.

L’infrastructure Kafka, Elasticsearch et Kibana est déployée avec Docker Compose, tandis que l’application Spring Boot est déployée avec Kubernetes sur AWS EC2.

---

# 🚀 Technologies utilisées

- Java 21
- Spring Boot
- Apache Kafka
- Elasticsearch
- Kibana
- Docker
- Docker Compose
- Kubernetes
- AWS EC2
- Maven

---

# 🏗️ Architecture du projet

```
OpenWeather API
        ↓
Spring Boot Producer
        ↓
Apache Kafka
        ↓
Spring Boot Consumer
        ↓
Elasticsearch
        ↓
Kibana Dashboard

## ⚙️ Fonctionnalités

✅ Streaming météo temps réel  
✅ Architecture Producer / Consumer Kafka  
✅ Transmission des données avec Apache Kafka  
✅ Indexation des données dans Elasticsearch  
✅ Visualisation des données avec Kibana  
✅ Conteneurisation avec Docker  
✅ Déploiement de l’application avec Kubernetes  
✅ Déploiement cloud sur AWS EC2  
✅ Monitoring et analyse des données météo  

---

## 📊 Dashboard Kibana

![Dashboard Kibana](screenshots/dashboard-kibana.png)

Le dashboard Kibana permet de visualiser :

- 🌡️ Température moyenne des villes
- 💧 Humidité moyenne des villes
- 📈 Évolution météo en temps réel
- 🌍 Répartition des événements par région
- 📡 Nombre d’événements météo traités
- 📋 Tableau temps réel des données météo

weather-app/
│
├── src/
├── k8s/
│   ├── weather-deployment.yaml
│   └── weather-service.yaml
│
├── screenshots/
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md

🐳 Lancer le projet en local

1️⃣ Cloner le projet
git clone https://github.com/dounia-lall/weather-app.git
cd weather-app

2️⃣ Démarrer l’infrastructure Docker
docker-compose up -d

Services démarrés :

Kafka
Elasticsearch
Kibana

3️⃣ Construire l’application Spring Boot
./mvnw clean package -DskipTests

4️⃣ Construire l’image Docker
docker build -t weather-app .

☸️ Déploiement Kubernetes

Déployer l’application avec Kubernetes :
kubectl apply -f k8s/weather-deployment.yaml
kubectl apply -f k8s/weather-service.yaml

Vérifier les pods :
kubectl get pods

Vérifier les services :
kubectl get services

📡 Tester l’API
curl -X POST http://localhost:30080/weather/Paris

📊 Accès Kibana
http://localhost:5601

☁️ Déploiement AWS

Le projet est déployé sur une instance AWS EC2 Ubuntu avec :

Docker
Kubernetes
Elasticsearch
Kibana
Apache Kafka

👩‍💻 Auteur
Dounia Lallouche

⭐ À propos
Application de streaming météo temps réel utilisant Spring Boot, Kafka, Elasticsearch, Kibana, Docker, Kubernetes et AWS.
