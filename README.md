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
        void randomize()

        T[] getSequence()
        void setSequence(T[] sequence)

        T getBaseAt(int index)
        void setBaseAt(int index, T base)

        int length()

Chromosome = series of genes

    interface Chromosome
        void randomize()

        Gene getGeneAt(int index)
        void setGeneAt(int index, Gene gene)

        Chromosome crossover(Chromosome other)
        void mutate(double probability)

        int length()

Population = collection of chromosomes

    interface Population
        void initialize()

        boolean add(Chromosome chromosome)

        int size()
        Iterator<Chromosome> iterator()

Function = arbitrary code that assigns fitness value to chromosome

    interface Function
        double evaluate(Chromosome chromosome)

Selector = logic that determines chromosomes used in next population

    interface Selector
        Chromosome[] select(int count, Function function, Population population)

Algorithm = process that controls how population evolves

    interface Algorithm
        Population evolve(Function function, Selector selector, Population population)

        boolean shouldTerminate()
