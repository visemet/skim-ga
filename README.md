skelgen
=======

A skeleton of a framework for writing genetic algorithms.

Motivation
----------
...

Structure
---------

### Models

Gene = sequence of bases

    interface Gene<T>
        T[] getSequence()
        void setSequence(T[] sequence)

        T getBaseAt(int index)
        void setBaseAt(int index, T base)

        int size()

Chromosome = series of genes

    interface Chromosome
        Chromosome crossover(Chromosome other)
        void mutate(double probability)

Population = collection of chromosomes

    interface Population
        ...

Function = arbitrary code that assigns fitness value to chromosome

    interface Function
        double evaluate(Chromosome chromosome)

Selector = logic that determines chromosomes used in next population

    interface Selector
        Chromosome[] select(int count, Function function, Population population)
