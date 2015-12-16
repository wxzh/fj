package fj.fjalg.internal;

public interface KlassVisitor<OKlass> extends fj.fjalg.shared.VisitKlass<OKlass, OKlass> {
	default OKlass visitKlass(OKlass e) {
		return e;
	}
}
