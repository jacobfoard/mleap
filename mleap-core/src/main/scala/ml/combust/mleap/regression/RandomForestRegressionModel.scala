package ml.combust.mleap.regression

import org.apache.spark.ml.linalg.Vector
import ml.combust.mleap.tree.TreeEnsemble

/**
 * Created by hwilkins on 11/8/15.
 */
case class RandomForestRegressionModel(trees: Seq[DecisionTreeRegressionModel], numFeatures: Int) extends TreeEnsemble {
  val numTrees = trees.length

  def apply(features: Vector): Double = predict(features)

  def predict(features: Vector): Double = {
    trees.map(_.predict(features)).sum / numTrees
  }

  override val treeWeights: Seq[Double] = Array.fill[Double](numTrees)(1.0)
}