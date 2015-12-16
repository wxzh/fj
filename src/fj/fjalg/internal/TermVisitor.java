package fj.fjalg.internal;

public interface TermVisitor<OTerm> extends fj.fjalg.shared.VisitTerm<OTerm, OTerm> {
	default OTerm visitTerm(OTerm e) {
		return e;
	}
}
