skim-ga
=======

A simple framework for writing genetic algorithms.

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

### The Flow
Consult [The Project Spot](http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3) for a more detailed description.

#### Outline
1. Initialize random population
2. Select chromosomes through fitness evaluation
3. Generate next population via crossover and mutation

#### Code Stubs

Main

    population.initialize();
    while (!algorithm.shouldTerminate()) {
        population = algorithm.evolve(function, selector, population);
    }

Algorithm

    ... implements Algorithm {
        ...

        Population evolve(Function function, Selector selector, Population population) {
            Population nextPopulation = ...;

            Chromosome[] chromosomes = selector.select(population.size(), function, population);
            for (int index = 0; index < chromosomes.length; index++) {
                Chromosome chromosome = chromosomes[i].crossover(chromosomes[(i + 1) % chromosomes.length]);
                chromosome.mutate(probability);

                nextPopulation.add(chromosome);
            }

            return nextPopulation;
        }

        ...
    }
