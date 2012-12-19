skim-ga
=======

A simple framework for writing genetic algorithms.

Motivation
----------

To study genetic algorithms and their ability to solve complex problems.

Structure
---------

> All the protein, without any of the fat!

### Interfaces

Individual = member of a population

    interface Individual
        void initialize()

        Individual copy()

Population = collection of individuals

    interface Population
        void initialize()

        void expand(Individual individual)

        void expand(List<Individual> individuals)

        int size()

        List<Individual> asList()

PopulationFactory = factory pattern to initialize populations

    interface PopulationFactory
        Population create()

        void expand(Population population)

FitnessFunction = arbitrary code that assigns fitness value to chromosome

    interface FitnessFunction
        double evaluate(Individual individual)

SelectionOperator = logic that determines individuals used in next population

    interface SelectionOperator
        List<Individual> select(Population population, FitnessFunction function)

CrossoverOperator = logic that recombines individuals for next population

    interface CrossoverOperator extends SelectionOperator

        List<Individual> crossover(List<Individual> individuals)

MutationOperator = logic that modifies individuals for next population

    interface MutationOperator extends SelectionOperator

        List<Individual> mutate(List<Individual> individuals)

GeneticAlgorithm = process that controls how population evolves

    interface GeneticAlgorithm
        SelectionOperator retrieveSelector();

        void apply(SelectionOperator selector);

        CrossoverOperator retrieveCrossover();

        void apply(CrossoverOperator crossover);

        MutationOperator retrieveMutator();

        void apply(MutationOperator mutator);

        void evolve(Population population, FitnessFunction function,
                    Population nextPopulation)

        boolean shouldTerminate()

### Configuration

    <simulation>
        <population-factory/>
        <fitness-function/>
        <genetic-algorithm>
            <selection-operator/>
            <crossover-operator/>
            <mutation-operator/>      
        </genetic-algorithm>
    </simulation>
