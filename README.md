# Analise de dados sensíveis

Essa é uma POC feita com o intuito de identificar o uso de dados sensíveis em projetos Java.

O objetivo inicial da POC é encontrar o uso de um vocabulário de dados sensiveis em classes que façam uso de conexão com banco de dados. 

Para realizar as análises, utilizamos a ferramenta [spoon](https://github.com/INRIA/spoon), que faz navegação e manipulação de ASTs.
