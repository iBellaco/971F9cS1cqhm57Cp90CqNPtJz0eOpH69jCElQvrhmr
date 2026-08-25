package com.example.ui.components;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FloatingAssistantOverlay.kt */
@Metadata(mv = {2, 2, 0}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"})
@DebugMetadata(f = "FloatingAssistantOverlay.kt", l = {251}, i = {0}, s = {"L$0"}, n = {"$this$pointerInput"}, m = "invokeSuspend", c = "com.example.ui.components.FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1")
/* loaded from: FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1.class */
public final class FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ MutableFloatState $dragOffsetX$delegate;
    final /* synthetic */ MutableFloatState $dragOffsetY$delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1(MutableFloatState $dragOffsetX$delegate, MutableFloatState $dragOffsetY$delegate, Continuation<? super FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1> continuation) {
        super(2, continuation);
        this.$dragOffsetX$delegate = $dragOffsetX$delegate;
        this.$dragOffsetY$delegate = $dragOffsetY$delegate;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1 = new FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1(this.$dragOffsetX$delegate, this.$dragOffsetY$delegate, continuation);
        floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1.L$0 = value;
        return floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1;
    }

    public final Object invoke(PointerInputScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                MutableFloatState mutableFloatState = this.$dragOffsetX$delegate;
                MutableFloatState mutableFloatState2 = this.$dragOffsetY$delegate;
                this.L$0 = SpillingKt.nullOutSpilledVariable($this$pointerInput);
                this.label = 1;
                if (DragGestureDetectorKt.detectDragGestures$default($this$pointerInput, (Function1) null, (Function0) null, (Function0) null, (v2, v3) -> {
                    return invokeSuspend$lambda$0(r4, r5, v2, v3);
                }, (Continuation) this, 7, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    private static final Unit invokeSuspend$lambda$0(MutableFloatState $dragOffsetX$delegate, MutableFloatState $dragOffsetY$delegate, PointerInputChange change, Offset dragAmount) {
        float FloatingAssistantOverlay$lambda$5;
        float FloatingAssistantOverlay$lambda$2;
        change.consume();
        FloatingAssistantOverlay$lambda$5 = FloatingAssistantOverlayKt.FloatingAssistantOverlay$lambda$5($dragOffsetX$delegate);
        $dragOffsetX$delegate.setFloatValue(FloatingAssistantOverlay$lambda$5 + Offset.getX-impl(dragAmount.unbox-impl()));
        FloatingAssistantOverlay$lambda$2 = FloatingAssistantOverlayKt.FloatingAssistantOverlay$lambda$2($dragOffsetY$delegate);
        $dragOffsetY$delegate.setFloatValue(FloatingAssistantOverlay$lambda$2 + Offset.getY-impl(dragAmount.unbox-impl()));
        return Unit.INSTANCE;
    }
}
