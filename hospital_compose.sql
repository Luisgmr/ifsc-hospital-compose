-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.3.67    Database: hospital_compose
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acompanhamento`
--

DROP TABLE IF EXISTS `acompanhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acompanhamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `grau_parentesco` varchar(100) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `fone` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acompanhamento`
--

LOCK TABLES `acompanhamento` WRITE;
/*!40000 ALTER TABLE `acompanhamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `acompanhamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acompanhante`
--

DROP TABLE IF EXISTS `acompanhante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acompanhante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `grau_parentesco` varchar(100) DEFAULT NULL,
  `cpf` varchar(20) NOT NULL,
  `fone` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acompanhante`
--

LOCK TABLES `acompanhante` WRITE;
/*!40000 ALTER TABLE `acompanhante` DISABLE KEYS */;
INSERT INTO `acompanhante` VALUES (1,'wefefw	uhui	','vfvfdv','dvfvvfd','vdfv','fdvfdv','fdvdf'),(2,'Pedro','Primeiro Grau','14143223421','48999999998','pedro@pedro.com','1');
/*!40000 ALTER TABLE `acompanhante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acompanhante_internacao`
--

DROP TABLE IF EXISTS `acompanhante_internacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acompanhante_internacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_entrada` datetime DEFAULT NULL,
  `data_saida` datetime DEFAULT NULL,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acompanhante_internacao`
--

LOCK TABLES `acompanhante_internacao` WRITE;
/*!40000 ALTER TABLE `acompanhante_internacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `acompanhante_internacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ala`
--

DROP TABLE IF EXISTS `ala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ala` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ala`
--

LOCK TABLES `ala` WRITE;
/*!40000 ALTER TABLE `ala` DISABLE KEYS */;
INSERT INTO `ala` VALUES (1,'Ala de Recém Chegados','Crítico'),(2,'Ala para pessoas Deficientes','Semicríticas'),(3,'Ala Teste 01','Urgência');
/*!40000 ALTER TABLE `ala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atendimento`
--

DROP TABLE IF EXISTS `atendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atendimento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_atendimento` datetime DEFAULT NULL,
  `pressao` varchar(50) DEFAULT NULL,
  `temperatura` varchar(10) DEFAULT NULL,
  `bpm` varchar(10) DEFAULT NULL,
  `oximetria` varchar(10) DEFAULT NULL,
  `historico_doencas` text,
  `alergias` text,
  `medicacoes_em_uso` text,
  `anamnese` text,
  `tipo_atendimento` varchar(50) DEFAULT NULL,
  `classificacao` varchar(50) DEFAULT NULL,
  `observacoes` text,
  `status` varchar(50) DEFAULT NULL,
  `paciente_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `enfermeiro_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `paciente_id` (`paciente_id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `enfermeiro_id` (`enfermeiro_id`),
  CONSTRAINT `atendimento_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`id`),
  CONSTRAINT `atendimento_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `atendimento_ibfk_3` FOREIGN KEY (`enfermeiro_id`) REFERENCES `enfermeiro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendimento`
--

LOCK TABLES `atendimento` WRITE;
/*!40000 ALTER TABLE `atendimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `atendimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `responsavel` varchar(255) DEFAULT NULL,
  `data_hora_consulta` datetime DEFAULT NULL,
  `anamnese` text,
  `diagnostico` text,
  `prescricao` text,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  `medico_id` bigint DEFAULT NULL,
  `atendimento_id` bigint DEFAULT NULL,
  `receita_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medico_id` (`medico_id`),
  KEY `atendimento_id` (`atendimento_id`),
  KEY `receita_id` (`receita_id`),
  CONSTRAINT `consulta_ibfk_1` FOREIGN KEY (`medico_id`) REFERENCES `medico` (`id`),
  CONSTRAINT `consulta_ibfk_2` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimento` (`id`),
  CONSTRAINT `consulta_ibfk_3` FOREIGN KEY (`receita_id`) REFERENCES `receita` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta_exame`
--

DROP TABLE IF EXISTS `consulta_exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta_exame` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_exame` datetime DEFAULT NULL,
  `analise_exame` text,
  `imagem_exame` text,
  `status` varchar(50) DEFAULT NULL,
  `consulta_id` bigint DEFAULT NULL,
  `exame_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `consulta_id` (`consulta_id`),
  KEY `exame_id` (`exame_id`),
  CONSTRAINT `consulta_exame_ibfk_1` FOREIGN KEY (`consulta_id`) REFERENCES `consulta` (`id`),
  CONSTRAINT `consulta_exame_ibfk_2` FOREIGN KEY (`exame_id`) REFERENCES `exame` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta_exame`
--

LOCK TABLES `consulta_exame` WRITE;
/*!40000 ALTER TABLE `consulta_exame` DISABLE KEYS */;
/*!40000 ALTER TABLE `consulta_exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enfermeiro`
--

DROP TABLE IF EXISTS `enfermeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enfermeiro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cre` varchar(50) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `nome_social` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `fone1` varchar(50) DEFAULT NULL,
  `fone2` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cpf_cnpj` varchar(50) DEFAULT NULL,
  `rg_inscricao_estadual` varchar(50) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `cep` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enfermeiro`
--

LOCK TABLES `enfermeiro` WRITE;
/*!40000 ALTER TABLE `enfermeiro` DISABLE KEYS */;
INSERT INTO `enfermeiro` VALUES (1,'23',NULL,'greregg',NULL,'gregregreg','regreg','regre','32ergre','234532423','4324324','2024-12-09','regreg','gergre','ergerg','regreg','greg','reger',NULL);
/*!40000 ALTER TABLE `enfermeiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exame`
--

DROP TABLE IF EXISTS `exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exame` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo_exame` varchar(255) DEFAULT NULL,
  `tipo_exame` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exame`
--

LOCK TABLES `exame` WRITE;
/*!40000 ALTER TABLE `exame` DISABLE KEYS */;
INSERT INTO `exame` VALUES (1,'Sérgio Ramos infultração Renal','Urina','Em análise'),(2,'Exame de prostata','Prostata','Em análise'),(3,'Exame da rita','COVID-19','Positivo');
/*!40000 ALTER TABLE `exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmaceutico`
--

DROP TABLE IF EXISTS `farmaceutico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `farmaceutico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cfr` varchar(50) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `nome_social` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `fone1` varchar(50) DEFAULT NULL,
  `fone2` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cpf_cnpj` varchar(50) DEFAULT NULL,
  `rg_inscricao_estadual` varchar(50) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `cep` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmaceutico`
--

LOCK TABLES `farmaceutico` WRITE;
/*!40000 ALTER TABLE `farmaceutico` DISABLE KEYS */;
/*!40000 ALTER TABLE `farmaceutico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome_fantasia` varchar(255) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internacao`
--

DROP TABLE IF EXISTS `internacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_internacao` datetime DEFAULT NULL,
  `data_hora_alta` datetime DEFAULT NULL,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  `consulta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `consulta_id` (`consulta_id`),
  CONSTRAINT `internacao_ibfk_1` FOREIGN KEY (`consulta_id`) REFERENCES `consulta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internacao`
--

LOCK TABLES `internacao` WRITE;
/*!40000 ALTER TABLE `internacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `internacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internacao_leito`
--

DROP TABLE IF EXISTS `internacao_leito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internacao_leito` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_alocacao` datetime DEFAULT NULL,
  `data_hora_desocupacao` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `internacao_id` bigint DEFAULT NULL,
  `leito_id` bigint DEFAULT NULL,
  `acompanhante_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `internacao_id` (`internacao_id`),
  KEY `leito_id` (`leito_id`),
  KEY `acompanhante_id` (`acompanhante_id`),
  CONSTRAINT `internacao_leito_ibfk_1` FOREIGN KEY (`internacao_id`) REFERENCES `internacao` (`id`),
  CONSTRAINT `internacao_leito_ibfk_2` FOREIGN KEY (`leito_id`) REFERENCES `leito` (`id`),
  CONSTRAINT `internacao_leito_ibfk_3` FOREIGN KEY (`acompanhante_id`) REFERENCES `acompanhante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internacao_leito`
--

LOCK TABLES `internacao_leito` WRITE;
/*!40000 ALTER TABLE `internacao_leito` DISABLE KEYS */;
/*!40000 ALTER TABLE `internacao_leito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jornada` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_inicial` date DEFAULT NULL,
  `carga_horaria` int DEFAULT NULL,
  `medico_id` bigint DEFAULT NULL,
  `enfermeiro_id` bigint DEFAULT NULL,
  `farmaceutico_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medico_id` (`medico_id`),
  KEY `enfermeiro_id` (`enfermeiro_id`),
  KEY `farmaceutico_id` (`farmaceutico_id`),
  CONSTRAINT `jornada_ibfk_1` FOREIGN KEY (`medico_id`) REFERENCES `medico` (`id`),
  CONSTRAINT `jornada_ibfk_2` FOREIGN KEY (`enfermeiro_id`) REFERENCES `enfermeiro` (`id`),
  CONSTRAINT `jornada_ibfk_3` FOREIGN KEY (`farmaceutico_id`) REFERENCES `farmaceutico` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorio`
--

DROP TABLE IF EXISTS `laboratorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome_fantasia` varchar(255) DEFAULT NULL,
  `contato` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorio`
--

LOCK TABLES `laboratorio` WRITE;
/*!40000 ALTER TABLE `laboratorio` DISABLE KEYS */;
INSERT INTO `laboratorio` VALUES (1,'S.T.A.R Labs','4877676776','Ativo');
/*!40000 ALTER TABLE `laboratorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorio_medicamento`
--

DROP TABLE IF EXISTS `laboratorio_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorio_medicamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_barras` varchar(255) DEFAULT NULL,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  `medicamento_id` bigint DEFAULT NULL,
  `laboratorio_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medicamento_id` (`medicamento_id`),
  KEY `laboratorio_id` (`laboratorio_id`),
  CONSTRAINT `laboratorio_medicamento_ibfk_1` FOREIGN KEY (`medicamento_id`) REFERENCES `medicamento` (`id`),
  CONSTRAINT `laboratorio_medicamento_ibfk_2` FOREIGN KEY (`laboratorio_id`) REFERENCES `laboratorio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorio_medicamento`
--

LOCK TABLES `laboratorio_medicamento` WRITE;
/*!40000 ALTER TABLE `laboratorio_medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboratorio_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leito`
--

DROP TABLE IF EXISTS `leito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leito` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `quarto_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quarto_id` (`quarto_id`),
  CONSTRAINT `leito_ibfk_1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leito`
--

LOCK TABLES `leito` WRITE;
/*!40000 ALTER TABLE `leito` DISABLE KEYS */;
/*!40000 ALTER TABLE `leito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `data_fabricacao` date DEFAULT NULL,
  `data_validade` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao_medicamento` varchar(255) DEFAULT NULL,
  `principio_ativo` varchar(255) DEFAULT NULL,
  `qtd_minima` float DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `crm` varchar(50) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `nome_social` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `fone1` varchar(50) DEFAULT NULL,
  `fone2` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cpf_cnpj` varchar(50) DEFAULT NULL,
  `rg_inscricao_estadual` varchar(50) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `cep` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES (1,'naosei','medico013','medico013','super mdicao','medico de souza',NULL,NULL,'efnjwif@how.com','2342342324','23423423',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'8974','rita medica','ewfjewof','jewifweof','rita medica',NULL,NULL,'hfweuif@fw.com','23082904',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimento_medicamento`
--

DROP TABLE IF EXISTS `movimento_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimento_medicamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_movimento` datetime DEFAULT NULL,
  `tipo_movimento` varchar(50) DEFAULT NULL,
  `qtd_medicamento` float DEFAULT NULL,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  `lote_id` bigint DEFAULT NULL,
  `laboratorio_id` bigint DEFAULT NULL,
  `receita_medicamento_id` bigint DEFAULT NULL,
  `prontuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lote_id` (`lote_id`),
  KEY `laboratorio_id` (`laboratorio_id`),
  KEY `receita_medicamento_id` (`receita_medicamento_id`),
  KEY `prontuario_id` (`prontuario_id`),
  CONSTRAINT `movimento_medicamento_ibfk_1` FOREIGN KEY (`lote_id`) REFERENCES `lote` (`id`),
  CONSTRAINT `movimento_medicamento_ibfk_2` FOREIGN KEY (`laboratorio_id`) REFERENCES `laboratorio` (`id`),
  CONSTRAINT `movimento_medicamento_ibfk_3` FOREIGN KEY (`receita_medicamento_id`) REFERENCES `receita_medicamento` (`id`),
  CONSTRAINT `movimento_medicamento_ibfk_4` FOREIGN KEY (`prontuario_id`) REFERENCES `prontuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimento_medicamento`
--

LOCK TABLES `movimento_medicamento` WRITE;
/*!40000 ALTER TABLE `movimento_medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimento_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `fone1` varchar(50) DEFAULT NULL,
  `fone2` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cpf_cnpj` varchar(50) DEFAULT NULL,
  `rg_inscricao_estadual` varchar(50) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `cep` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `tipo_sanguineo` varchar(10) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `nome_social` varchar(255) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (2,'João Silva',NULL,NULL,'joao.silva@example.com','12345678909','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'O+','Masculino',NULL,'1980-05-23',NULL),(3,'Maria Oliveira',NULL,NULL,'maria.oliveira@example.com','98765432100','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'A-','Feminino',NULL,'1975-09-12',NULL),(4,'Carlos Pereira da Silva de Souza Machado',NULL,NULL,'carlos.pereira@example.com','45678912345','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'B+','Masculino',NULL,'1990-11-08',NULL),(5,'Ana Santos',NULL,NULL,'ana.santos@example.com','78912345678','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'AB-','Feminino',NULL,'1985-03-14',NULL),(7,'Fernanda Lima','48999999999','','fernanda.lima@example.com','65432198767','448172161','2024-12-09','fewokjfeiwo','88745000','pedro martins cipriano','cacador','eoijfewiofjiew','wefnjwj','O-','Feminino',NULL,'1998-06-10','SC'),(8,'Juliana Barros',NULL,NULL,'juliana.barros@example.com','21354687930','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'A-','Feminino',NULL,'1983-12-03',NULL),(9,'Pedro Ramos',NULL,NULL,'pedro.ramos@example.com','32548769122','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'B+','Masculino',NULL,'1978-09-15',NULL),(10,'Clara Almeida',NULL,NULL,'clara.almeida@example.com','78965432145','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'AB+','Feminino',NULL,'1992-04-28',NULL),(11,'Rafael Lima',NULL,NULL,'rafael.lima@example.com','45612378956','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'O-','Masculino',NULL,'1987-07-19',NULL),(12,'Larissa Gomes',NULL,NULL,'larissa.gomes@example.com','31265498778','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'A+','Feminino',NULL,'1993-11-04',NULL),(13,'Thiago Martins',NULL,NULL,'thiago.martins@example.com','98732165409','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'O+','Masculino',NULL,'1984-02-17',NULL),(14,'Sabrina Costa',NULL,NULL,'sabrina.costa@example.com','45698712333','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'AB-','Feminino',NULL,'1990-06-22',NULL),(15,'Gabriel Sousa',NULL,NULL,'gabriel.sousa@example.com','65412378955','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'B-','Masculino',NULL,'1985-03-09',NULL),(16,'Helena Rocha',NULL,NULL,'helena.rocha@example.com','32145698712','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'A-','Feminino',NULL,'1998-08-16',NULL),(17,'Marcos Silva',NULL,NULL,'marcos.silva@example.com','21365498745','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'AB+','Masculino',NULL,'1979-12-20',NULL),(18,'Beatriz Andrade',NULL,NULL,'beatriz.andrade@example.com','78932165433','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'O-','Feminino',NULL,'1986-01-11',NULL),(20,'Patrícia Mendes',NULL,NULL,'patricia.mendes@example.com','45632198798','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'A+','Feminino',NULL,'1994-10-14',NULL),(21,'Rodrigo Nunes',NULL,NULL,'rodrigo.nunes@example.com','65498732177','448172161','2024-12-07',NULL,NULL,NULL,NULL,NULL,NULL,'O+','Masculino',NULL,'1989-07-30',NULL),(27,'Luis Gustavo','48997294597','','fioewjfio@hotmail.com','12405898918','239489328','2024-12-09','wefewf','88745000','ewfewf','ewfewf','wejfhewiufh','ewfew','O-','Masculino',NULL,'2004-07-01',NULL);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prontuario`
--

DROP TABLE IF EXISTS `prontuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prontuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_visita` datetime DEFAULT NULL,
  `descricao_visita` text,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  `internacao_leito_id` bigint DEFAULT NULL,
  `enfermeiro_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `internacao_leito_id` (`internacao_leito_id`),
  KEY `enfermeiro_id` (`enfermeiro_id`),
  CONSTRAINT `prontuario_ibfk_1` FOREIGN KEY (`internacao_leito_id`) REFERENCES `internacao_leito` (`id`),
  CONSTRAINT `prontuario_ibfk_2` FOREIGN KEY (`enfermeiro_id`) REFERENCES `enfermeiro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prontuario`
--

LOCK TABLES `prontuario` WRITE;
/*!40000 ALTER TABLE `prontuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `prontuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarto`
--

DROP TABLE IF EXISTS `quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `ala_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ala_id` (`ala_id`),
  CONSTRAINT `quarto_ibfk_1` FOREIGN KEY (`ala_id`) REFERENCES `ala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarto`
--

LOCK TABLES `quarto` WRITE;
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
INSERT INTO `quarto` VALUES (2,'Quarto 01','Disponível',1);
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receita`
--

DROP TABLE IF EXISTS `receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receita` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_receita` datetime DEFAULT NULL,
  `observacao` text,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receita`
--

LOCK TABLES `receita` WRITE;
/*!40000 ALTER TABLE `receita` DISABLE KEYS */;
/*!40000 ALTER TABLE `receita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receita_medicamento`
--

DROP TABLE IF EXISTS `receita_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receita_medicamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prescricao` text,
  `status` varchar(50) DEFAULT NULL,
  `receita_id` bigint DEFAULT NULL,
  `medicamento_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `receita_id` (`receita_id`),
  KEY `medicamento_id` (`medicamento_id`),
  CONSTRAINT `receita_medicamento_ibfk_1` FOREIGN KEY (`receita_id`) REFERENCES `receita` (`id`),
  CONSTRAINT `receita_medicamento_ibfk_2` FOREIGN KEY (`medicamento_id`) REFERENCES `medicamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receita_medicamento`
--

LOCK TABLES `receita_medicamento` WRITE;
/*!40000 ALTER TABLE `receita_medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `receita_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `fone1` varchar(50) DEFAULT NULL,
  `fone2` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cpf_cnpj` varchar(50) DEFAULT NULL,
  `rg_inscricao_estadual` varchar(50) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `cep` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `nome_social` varchar(255) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hospital_compose'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-13 10:35:39
