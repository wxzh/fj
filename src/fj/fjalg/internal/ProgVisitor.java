package fj.fjalg.internal;

public interface ProgVisitor<OProg> extends fj.fjalg.shared.VisitProg<OProg, OProg> {
	default OProg visitProg(OProg e) {
		return e;
	}
}
