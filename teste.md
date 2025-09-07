```
pesoLiquidoFinalDaPreparacao = (SE rendimentoDigitado > 0 ENTÃO rendimentoDigitado 
                                                          SENÃO totalPesoLiquido * (1 + percentualDeAguaAdicionada))
       
fccGeral = (SE totalPesoLiquido > 0 ENTÃO pesoLiquidoFinalDaPreparacao / totalPesoLiquido SENÃO 1.0)
```



```
proteinaKcal = proteinaPorPorcao * 4
carboidratoKcal = carboidratoPorPorcao * 4
lipidioKcal = lipidioPorPorcao * 9
totalMacrosKcal = proteinaKcal + carboidratoKcal + lipidioKcal
```
