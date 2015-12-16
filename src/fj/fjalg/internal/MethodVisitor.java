package fj.fjalg.internal;

public interface MethodVisitor<OMethod> extends fj.fjalg.shared.VisitMethod<OMethod, OMethod> {
	default OMethod visitMethod(OMethod e) {
		return e;
	}
}
