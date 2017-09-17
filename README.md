# air-is-really-problematic
short for: Paper Airplane Project Eventually Realizes Air Is Really Problematic, by Landon, Alex, Nicholas, and Especially Samantha

### A HackMIT 2017 Submission
P.A.P.E.R.A.I.R.P.L.A.N.E.S. was made in less than 24 hours.

Summary: Given a paper size and desired relative throwing velocity, our algorithm will create several "species" of planes, and selectively "breed" them to encourage formation of characteristics that perform well (fly the furthest) in a physical model. The project will output a paper folding pattern for an optimal plane which can be intuitively constructed by the end user.

Theoretical description of components:

Encoding tool for defining sets of folds mathematically.
Interpreting tool to convert fold vectors to points and polygons.
Physical parameter modeling tool to calculate the relevant physical properties of the origami manifold for aerodynamic simulation.
Aerodynamic and kinematic simulation of origami models to determine flight distance, factoring in drag, vortex lift, standard lift, gravity, and initial velocity conditions. Conditions are checked from all angles to ensure that creative solutions are able to succeed.
Genetic algorithm to evaluate breed individual plane "species" with the best performance (determined by simulation), and create successive sets of airplanes with incrementally optimized performance.
Accomplished:

Completed genetic selection model for processing sets of plane species and selectively mutating those with proven favorable characteristics.
In-depth evaluation of computational modeling of folding patterns.
Computational model of 2-D representation of sequential folding of paper.
Partial aerodynamic and kinematic modeling of the bi-modal distribution of plane species with vortex and standard lift.
Needs future work/assessment:

User interface currently not yet implemented.
Kinematic simulation solves for predicted optimum ratios of forces instead of an actual distance.
Currently no translation tool between fold vectors and polygons.
Currently no interpreter to assess direct aerodynamically relevant data from species parameters.
