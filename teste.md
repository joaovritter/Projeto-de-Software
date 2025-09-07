```
pesoLiquidoFinalDaPreparacao = (SE rendimentoDigitado > 0 ENTÃO rendimentoDigitado 
                                                          SENÃO totalPesoLiquido * (1 + percentualDeAguaAdicionada))
       
fccGeral = (SE totalPesoLiquido > 0 ENTÃO pesoLiquidoFinalDaPreparacao / totalPesoLiquido SENÃO 1.0)
```
