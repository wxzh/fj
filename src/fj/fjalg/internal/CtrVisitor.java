package fj.fjalg.internal;

public interface CtrVisitor<OCtr> extends fj.fjalg.shared.VisitCtr<OCtr, OCtr> {
	default OCtr visitCtr(OCtr e) {
		return e;
	}
}
