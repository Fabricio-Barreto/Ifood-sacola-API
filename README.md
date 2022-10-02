# Ifood-sacola-API
 
API endpoints summary
Route                                       | Method   | Description
--------------------------------------------|----------|--------------------
/ifood/api/sacolas                          | POST     | Inclui item na Sacola
/ifood/api/sacolas/{id}                     | GET      | Lê Sacola pelo ID
/ifood/api/sacolas/fecharSacola/{sacolaId}  | PATCH    | Fecha Sacola



### POST localhost:8081/ifood/api/sacolas  
##### HTTP Request Body Example

    {
      "idSacola": 1,
      "produtoId": 1,
      "quantidade": 2
    }

##### HTTP Response Body Example

    {
      "id": 1,
      "produto": {
        "disponivel": true,
        "id": 1,
        "nome": "string",
        "valorUnitario": 5
      },
      "quantidade": 2
    }

### GET localhost:8081/ifood/api/sacolas/1   
##### HTTP Request Body Example
N/A

##### HTTP Response Body Example
    {
      "id": 1,
      "itens": [
        {
          "id": 1,
          "produto": {
            "id": 1,
            "nome": "Produto 1",
            "valorUnitario": 5,
            "disponivel": true
          },
          "quantidade": 2
        }
      ],
      "valorTotal": 10,
      "formaPagamento": "DINHEIRO",
      "fechada": false
    }
    
### PATCH localhost:8081/ifood/api/sacolas/fecharSacola/1  
##### HTTP Request Body Example
N/A

##### HTTP Response Body Example

Request URL
http://localhost:8081/ifood/api/sacolas/fecharSacola/1?formaPagamento=1

    {
      "id": 1,
      "itens": [
        {
          "id": 1,
          "produto": {
            "id": 1,
            "nome": "Produto 1",
            "valorUnitario": 5,
            "disponivel": true
          },
          "quantidade": 2
        }
      ],
      "valorTotal": 10,
      "formaPagamento": "MAQUINETA",
      "fechada": true
    }
