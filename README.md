An implementation of the [wave function collapse](https://en.wikipedia.org/wiki/Model_synthesis) algorithm.

## Rule Encoding
The implementation represents the constraints of the model as a set of rules. Each rule represents a valid pair of neighboring cells. Namely, it encapsulates a target cell's state, a valid neighbor cell's state, and the cardinal direction from the target to the neighbor. The set of rules is one-hot encoded in a 3D matrix. The target cell's state, neighbor cell's state, and direction being the axes of the matrix.

## Iteration
Each iteration of the algorithm selects the cell with the lowest entropy. If there are several of the same, lowest, entropy, one of them is chosen randomly. The entropy is calculated using the [Shannon entropy](https://en.wikipedia.org/wiki/Entropy_(information_theory)). The chosen cell is randomly collapsed to one of its remaining, valid states. Cells with no valid states are left as undetermined. 

## Example
![example.gif](https://github.com/a-moseman/WaveFunctionCollapse/blob/master/example.gif?raw=true)
