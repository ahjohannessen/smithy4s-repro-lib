/*
 * Copyright 2022 Alex Henning Johannessen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package s4s
package data

import cats.syntax.all._

sealed abstract case class Fraction(basisPoint: Int)
object Fraction {
  private val maxBp: Int = 10000
  private val minBp: Int = 0

  private def create(basisPoint: Int): Fraction =
    new Fraction(basisPoint) {}

  def fromBasisPoint(bp: Int): Either[String, Fraction] =
    if (minBp <= bp && bp <= maxBp) create(bp).asRight
    else "Basis point has invalid range".asLeft

}
