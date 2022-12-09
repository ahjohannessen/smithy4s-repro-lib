$version: "2"

metadata suppressions = [
    {
        id: "UnreferencedShape",
        namespace: "s4s.smithy",
        reason: "This is a library namespace."
    }
]

namespace s4s.smithy

use smithy4s.meta#refinement
use smithy4s.meta#unwrap

apply fractionFormat @refinement(
  targetType: "s4s.data.Fraction",
  providerImport: "s4s.refinements._"
)

@trait(selector: "integer")
structure fractionFormat {}

@fractionFormat
@unwrap
integer Fraction
