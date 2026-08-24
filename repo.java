/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  androidx.compose.runtime.internal.StabilityInferred
 *  io.github.jan.supabase.SupabaseClient
 *  io.github.jan.supabase.postgrest.Postgrest
 *  io.github.jan.supabase.postgrest.Postgrest$Config
 *  io.github.jan.supabase.postgrest.PostgrestKt
 *  io.github.jan.supabase.postgrest.UtilsKt
 *  io.github.jan.supabase.postgrest.executor.RestRequestExecutor
 *  io.github.jan.supabase.postgrest.query.Columns
 *  io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
 *  io.github.jan.supabase.postgrest.query.PostgrestRequestBuilder
 *  io.github.jan.supabase.postgrest.query.filter.PostgrestFilterBuilder
 *  io.github.jan.supabase.postgrest.query.request.SelectRequestBuilder
 *  io.github.jan.supabase.postgrest.query.request.UpsertRequestBuilder
 *  io.github.jan.supabase.postgrest.request.DeleteRequest
 *  io.github.jan.supabase.postgrest.request.InsertRequest
 *  io.github.jan.supabase.postgrest.request.PostgrestRequest
 *  io.github.jan.supabase.postgrest.request.SelectRequest
 *  io.github.jan.supabase.postgrest.result.PostgrestResult
 *  io.ktor.http.Headers
 *  kotlin.Metadata
 *  kotlin.Result
 *  kotlin.ResultKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.coroutines.Continuation
 *  kotlin.coroutines.CoroutineContext
 *  kotlin.coroutines.intrinsics.IntrinsicsKt
 *  kotlin.coroutines.jvm.internal.Boxing
 *  kotlin.coroutines.jvm.internal.ContinuationImpl
 *  kotlin.coroutines.jvm.internal.SpillingKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.functions.Function3
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.Reflection
 *  kotlin.ranges.RangesKt
 *  kotlin.reflect.KTypeProjection
 *  kotlin.text.StringsKt
 *  kotlinx.coroutines.BuildersKt
 *  kotlinx.coroutines.CoroutineScope
 *  kotlinx.coroutines.Dispatchers
 *  kotlinx.serialization.DeserializationStrategy
 *  kotlinx.serialization.json.Json
 *  kotlinx.serialization.json.JsonArray
 *  kotlinx.serialization.json.JsonElement
 *  kotlinx.serialization.json.JsonElementKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.example.data.supabase;

import android.content.Context;
import android.util.Log;
import androidx.compose.runtime.internal.StabilityInferred;
import com.example.data.WildRiftRepository;
import com.example.data.supabase.SupabaseClientManager;
import com.example.data.supabase.WildRiftSupabaseRepository;
import com.example.data.supabase.model.WrChampionDto;
import com.example.data.supabase.model.WrItemDto;
import com.example.data.supabase.model.WrPatchDto;
import com.example.data.supabase.model.WrRuneDto;
import com.example.data.supabase.model.WrSpellDto;
import com.example.model.Champion;
import com.example.model.RuneItem;
import com.example.model.SummonerSpellItem;
import com.example.model.WildRiftItem;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.postgrest.Postgrest;
import io.github.jan.supabase.postgrest.PostgrestKt;
import io.github.jan.supabase.postgrest.UtilsKt;
import io.github.jan.supabase.postgrest.executor.RestRequestExecutor;
import io.github.jan.supabase.postgrest.query.Columns;
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder;
import io.github.jan.supabase.postgrest.query.PostgrestRequestBuilder;
import io.github.jan.supabase.postgrest.query.filter.PostgrestFilterBuilder;
import io.github.jan.supabase.postgrest.query.request.SelectRequestBuilder;
import io.github.jan.supabase.postgrest.query.request.UpsertRequestBuilder;
import io.github.jan.supabase.postgrest.request.DeleteRequest;
import io.github.jan.supabase.postgrest.request.InsertRequest;
import io.github.jan.supabase.postgrest.request.PostgrestRequest;
import io.github.jan.supabase.postgrest.request.SelectRequest;
import io.github.jan.supabase.postgrest.result.PostgrestResult;
import io.ktor.http.Headers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KTypeProjection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={2, 2, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0086@\u00a2\u0006\u0004\b\u0012\u0010\u0013J&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u0010H\u0086@\u00a2\u0006\u0004\b\u001d\u0010\u0013J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u001f\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0004\b \u0010!J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010#\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0004\b$\u0010%J\u001c\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u001b0\u0010H\u0086@\u00a2\u0006\u0004\b(\u0010\u0013J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010*\u001a\u00020'H\u0086@\u00a2\u0006\u0004\b+\u0010,J\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010.\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0004\b/\u0010%J$\u00100\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00020'0\u001bH\u0086@\u00a2\u0006\u0004\b2\u00103J\u001c\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u001b0\u0010H\u0086@\u00a2\u0006\u0004\b6\u0010\u0013J\u001e\u00107\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u00108\u001a\u000205H\u0086@\u00a2\u0006\u0004\b9\u0010:J\u001e\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010<\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0004\b=\u0010%J\u001c\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u001b0\u0010H\u0086@\u00a2\u0006\u0004\b@\u0010\u0013J\u001e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010B\u001a\u00020?H\u0086@\u00a2\u0006\u0004\bC\u0010DJ\u001e\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010F\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0004\bG\u0010%J\u001e\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010I\u001a\u00020JH\u0086@\u00a2\u0006\u0004\bK\u0010LJc\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102K\u0010N\u001aG\u0012\u0013\u0012\u00110P\u00a2\u0006\f\bQ\u0012\b\bR\u0012\u0004\b\b(S\u0012\u0013\u0012\u00110P\u00a2\u0006\f\bQ\u0012\b\bR\u0012\u0004\b\b(T\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\bQ\u0012\b\bR\u0012\u0004\b\b(U\u0012\u0004\u0012\u00020\u00150OH\u0086@\u00a2\u0006\u0004\bV\u0010WR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006X"}, d2={"Lcom/example/data/supabase/WildRiftSupabaseRepository;", "", "<init>", "()V", "TAG", "", "TABLE_PATCHES", "TABLE_ITEMS", "TABLE_CHAMPIONS", "TABLE_RUNES", "TABLE_SPELLS", "postgrest", "Lio/github/jan/supabase/postgrest/Postgrest;", "getPostgrest", "()Lio/github/jan/supabase/postgrest/Postgrest;", "fetchCurrentPatch", "Lkotlin/Result;", "Lcom/example/data/supabase/model/WrPatchDto;", "fetchCurrentPatch-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "publishPatch", "", "version", "notes", "publishPatch-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAllItems", "", "Lcom/example/model/WildRiftItem;", "fetchAllItems-IoAF18A", "saveItem", "item", "saveItem-gIAlu-s", "(Lcom/example/model/WildRiftItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteItem", "itemId", "deleteItem-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAllChampions", "Lcom/example/model/Champion;", "fetchAllChampions-IoAF18A", "saveChampion", "champion", "saveChampion-gIAlu-s", "(Lcom/example/model/Champion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteChampion", "championId", "deleteChampion-gIAlu-s", "saveAllChampionsToSupabase", "champions", "saveAllChampionsToSupabase-gIAlu-s", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAllRunes", "Lcom/example/model/RuneItem;", "fetchAllRunes-IoAF18A", "saveRune", "rune", "saveRune-gIAlu-s", "(Lcom/example/model/RuneItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRune", "runeId", "deleteRune-gIAlu-s", "fetchAllSpells", "Lcom/example/model/SummonerSpellItem;", "fetchAllSpells-IoAF18A", "saveSpell", "spell", "saveSpell-gIAlu-s", "(Lcom/example/model/SummonerSpellItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSpell", "spellId", "deleteSpell-gIAlu-s", "syncAllFromSupabase", "context", "Landroid/content/Context;", "syncAllFromSupabase-gIAlu-s", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedAllDataToSupabase", "onProgress", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "current", "total", "message", "seedAllDataToSupabase-gIAlu-s", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"})
@StabilityInferred(parameters=1)
public final class WildRiftSupabaseRepository {
    @NotNull
    public static final WildRiftSupabaseRepository INSTANCE = new WildRiftSupabaseRepository();
    @NotNull
    private static final String TAG = "WildRiftSupabaseRepo";
    @NotNull
    private static final String TABLE_PATCHES = "wr_patches";
    @NotNull
    private static final String TABLE_ITEMS = "wr_items";
    @NotNull
    private static final String TABLE_CHAMPIONS = "wr_champions";
    @NotNull
    private static final String TABLE_RUNES = "wr_runes";
    @NotNull
    private static final String TABLE_SPELLS = "wr_spells";
    public static final int $stable;

    private WildRiftSupabaseRepository() {
    }

    private final Postgrest getPostgrest() {
        return PostgrestKt.getPostgrest((SupabaseClient)SupabaseClientManager.INSTANCE.getClient());
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object fetchCurrentPatch-IoAF18A(@NotNull Continuation<? super Result<WrPatchDto>> $completion) {
        if (!($completion instanceof fetchCurrentPatch.1)) ** GOTO lbl-1000
        var3_2 = $completion;
        if ((var3_2.label & -2147483648) != 0) {
            var3_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.fetchCurrentPatch-IoAF18A((Continuation<? super Result<WrPatchDto>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var4_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends WrPatchDto>>, Object>(null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int I$0;
                    int label;

                    /*
                     * Unable to fully structure code
                     */
                    public final Object invokeSuspend(Object $result) {
                        var13_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                $this\1 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_patches");
                                columns\1 = Columns.Companion.getALL-U9NzzuM();
                                $i$f$select-Ao2T0zE\1\42 = 0;
                                var7_9 = new SelectRequestBuilder(((Postgrest.Config)$this\1.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $this$select_Ao2T0zE_u24lambda_u240\1 = var7_9;
                                $i$a$-apply-PostgrestQueryBuilder$select$requestBuilder$1\2\408\1 = false;
                                var10_13 = $this$select_Ao2T0zE_u24lambda_u240\1;
                                $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = false;
                                <this>\3 = var10_13;
                                $this$select_Ao2T0zE_u24lambda_u240\1.getParams().put("select", CollectionsKt.listOf((Object)columns\1));
                                requestBuilder\1 = var7_9;
                                selectRequest\1 = new SelectRequest(requestBuilder\1.getHead(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), $this\1.getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)columns\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)selectRequest\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$select-Ao2T0zE\1\42;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute($this\1.getPostgrest(), $this\1.getTable(), (PostgrestRequest)selectRequest\1, (Continuation)this);
                                ** if (v0 != var13_2) goto lbl31
lbl30:
                                // 1 sources

                                return var13_2;
lbl31:
                                // 1 sources

                                ** GOTO lbl43
                            }
                            case 1: {
                                $i$f$select-Ao2T0zE\1\42 = this.I$0;
                                requestBuilder\1 = (SelectRequestBuilder)this.L$3;
                                selectRequest\1 = (SelectRequest)this.L$2;
                                columns\1 = (String)this.L$1;
                                $this\1 = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl43:
                                    // 2 sources

                                    this_\4 = (PostgrestResult)v0;
                                    $i$f$decodeList\4\42 = false;
                                    this_\5 = this_\4;
                                    $i$f$decodeAs\5\420 = false;
                                    selectRequest\1 = this_\5.getPostgrest().getSerializer();
                                    value\6 = this_\5.getData();
                                    $i$f$decode\6\421 = false;
                                    list = (List)$this$decode\6.decode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrPatchDto.class))), value\6);
                                    var6_8 = list;
                                    for (E var8_10 : var6_8) {
                                        it\8 = (WrPatchDto)var8_10;
                                        $i$a$-find-WildRiftSupabaseRepository$fetchCurrentPatch$2$current$1\8\43\0 = false;
                                        if (!Intrinsics.areEqual((Object)it\8.getId(), (Object)"current")) continue;
                                        v1 = var8_10;
                                        ** GOTO lbl62
                                    }
                                    v1 = null;
lbl62:
                                    // 2 sources

                                    if ((v2 = (WrPatchDto)v1) == null) {
                                        v2 = (WrPatchDto)CollectionsKt.firstOrNull((List)list);
                                    }
                                    current = v2;
                                    var2_18 = Result.constructor-impl((Object)current);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error obteniendo parche de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_18 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_18);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<WrPatchDto>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var4_4) {
                    return var4_4;
                }
                ** GOTO lbl20
            }
            case 1: {
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl20:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object publishPatch-0E7RQCE(@NotNull String version, @NotNull String notes, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof publishPatch.1)) ** GOTO lbl-1000
        var5_4 = $completion;
        if ((var5_4.label & -2147483648) != 0) {
            var5_4.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                Object L$1;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.publishPatch-0E7RQCE(null, null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var6_6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)version);
                $continuation.L$1 = SpillingKt.nullOutSpilledVariable((Object)notes);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(version, notes, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    Object L$9;
                    int I$0;
                    int I$1;
                    int label;
                    final /* synthetic */ String $version;
                    final /* synthetic */ String $notes;
                    {
                        this.$version = $version;
                        this.$notes = $notes;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var25_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                $this$invokeSuspend_u24lambda_u240\1 = var3_3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                                $i$a$-apply-WildRiftSupabaseRepository$publishPatch$2$sdf$1\1\53\0 = false;
                                $this$invokeSuspend_u24lambda_u240\1.setTimeZone(TimeZone.getTimeZone("UTC"));
                                sdf = var3_3;
                                patchDto = new WrPatchDto("current", StringsKt.trim((CharSequence)this.$version).toString(), StringsKt.trim((CharSequence)this.$notes).toString(), sdf.format(new Date()));
                                $this$invokeSuspend_u24lambda_u240\1 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_patches");
                                value\2 = patchDto;
                                $i$f$upsert\2\62 = 0;
                                var7_14 = $this\2;
                                values\3 = CollectionsKt.listOf((Object)value\2);
                                $i$f$upsert\3\407 = 0;
                                var11_22 = var10_20 = new UpsertRequestBuilder(((Postgrest.Config)this_\3.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-upsert-PostgrestQueryBuilder$upsert$5\11\408\0 = false;
                                <this>\11 = var11_22;
                                requestBuilder\3 = var10_20;
                                $this$encodeToJsonElement\4 = this_\3.getPostgrest().getSerializer();
                                $i$f$encodeToJsonElement\4\410 = false;
                                var16_31 = (Json)Json.Default;
                                $this$encode\5 /* !! */  = $this$encodeToJsonElement\4;
                                $i$f$encode\5\411 = false;
                                $this$encode\5 /* !! */  = $this$encode\5 /* !! */ .encode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrPatchDto.class))), (Object)values\3);
                                $i$f$decodeFromString\6\411 = false;
                                this_\6.getSerializersModule();
                                body\3 = JsonElementKt.getJsonArray((JsonElement)((JsonElement)this_\6.decodeFromString((DeserializationStrategy)JsonElement.Companion.serializer(), (String)string\6)));
                                $this$map\7 = (Iterable)body\3;
                                $i$f$map\7\414 = false;
                                string\6 = $this$map\7;
                                destination\8 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\7, (int)10));
                                $i$f$mapTo\8\415 = false;
                                for (T item\8 : $this$mapTo\8) {
                                    var22_43 = (JsonElement)item\8;
                                    var23_44 = destination\8;
                                    $i$a$-map-PostgrestQueryBuilder$upsert$columns$1\9\417\3 = false;
                                    var23_44.add(JsonElementKt.getJsonObject((JsonElement)it\9).keySet());
                                }
                                columns\3 = CollectionsKt.distinct((Iterable)CollectionsKt.flatten((Iterable)((List)destination\8)));
                                if (((Collection)columns\3).isEmpty() == false) {
                                    requestBuilder\3.getParams().put("columns", CollectionsKt.listOf((Object)CollectionsKt.joinToString$default((Iterable)columns\3, (CharSequence)",", null, null, (int)0, null, null, (int)62, null)));
                                }
                                v0 = requestBuilder\3.getOnConflict();
                                if (v0 != null) {
                                    it\3 = v0;
                                    $i$a$-let-PostgrestQueryBuilder$upsert$3\10\420\3 = false;
                                    requestBuilder\3.getParams().put("on_conflict", CollectionsKt.listOf((Object)it\3));
                                }
                                var16_33 = requestBuilder\3.getReturning();
                                var17_34 = requestBuilder\3.getCount();
                                var18_38 = UtilsKt.mapToFirstValue((Map)requestBuilder\3.getParams());
                                var19_39 = requestBuilder\3.getDefaultToNull();
                                var20_41 = requestBuilder\3.getIgnoreDuplicates();
                                var21_42 = this_\3.getSchema();
                                var22_43 = requestBuilder\3.getHeaders().build();
                                insertRequest\3 = new InsertRequest(true, var16_33, var17_34, var20_41 != false, var19_39 != false, body\3, var18_38, var21_42, (Headers)var22_43);
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)sdf);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)patchDto);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)$this\2);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)value\2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable((Object)this_\3);
                                this.L$5 = SpillingKt.nullOutSpilledVariable((Object)values\3);
                                this.L$6 = SpillingKt.nullOutSpilledVariable((Object)body\3);
                                this.L$7 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\3);
                                this.L$8 = SpillingKt.nullOutSpilledVariable((Object)columns\3);
                                this.L$9 = SpillingKt.nullOutSpilledVariable((Object)insertRequest\3);
                                this.I$0 = $i$f$upsert\2\62;
                                this.I$1 = $i$f$upsert\3\407;
                                this.label = 1;
                                v1 = RestRequestExecutor.INSTANCE.execute(this_\3.getPostgrest(), this_\3.getTable(), (PostgrestRequest)insertRequest\3, (Continuation)this);
                                ** if (v1 != var25_2) goto lbl83
lbl82:
                                // 1 sources

                                return var25_2;
lbl83:
                                // 1 sources

                                ** GOTO lbl103
                            }
                            case 1: {
                                $i$f$upsert\3\407 = this.I$1;
                                $i$f$upsert\2\62 = this.I$0;
                                insertRequest\3 = (InsertRequest)this.L$9;
                                columns\3 = (List)this.L$8;
                                requestBuilder\3 = (UpsertRequestBuilder)this.L$7;
                                body\3 = (JsonArray)this.L$6;
                                values\3 = (List)this.L$5;
                                this_\3 = (PostgrestQueryBuilder)this.L$4;
                                value\2 = (WrPatchDto)this.L$3;
                                $this\2 = (PostgrestQueryBuilder)this.L$2;
                                patchDto = (WrPatchDto)this.L$1;
                                sdf = (SimpleDateFormat)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v1 = $result;
lbl103:
                                    // 2 sources

                                    WildRiftRepository.INSTANCE.setCURRENT_PATCH_VERSION(StringsKt.trim((CharSequence)this.$version).toString());
                                    var2_11 = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error publicando parche en Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_11 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_11);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var6_6) {
                    return var6_6;
                }
                ** GOTO lbl24
            }
            case 1: {
                notes = (String)$continuation.L$1;
                version = (String)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl24:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object fetchAllItems-IoAF18A(@NotNull Continuation<? super Result<? extends List<WildRiftItem>>> $completion) {
        if (!($completion instanceof fetchAllItems.1)) ** GOTO lbl-1000
        var3_2 = $completion;
        if ((var3_2.label & -2147483648) != 0) {
            var3_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.fetchAllItems-IoAF18A((Continuation<? super Result<? extends List<WildRiftItem>>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var4_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends List<? extends WildRiftItem>>>, Object>(null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int I$0;
                    int label;

                    /*
                     * Unable to fully structure code
                     */
                    public final Object invokeSuspend(Object $result) {
                        var14_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                $this\1 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_items");
                                columns\1 = Columns.Companion.getALL-U9NzzuM();
                                $i$f$select-Ao2T0zE\1\77 = 0;
                                $this$select_Ao2T0zE_u24lambda_u240\1 = var7_10 = new SelectRequestBuilder(((Postgrest.Config)$this\1.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-apply-PostgrestQueryBuilder$select$requestBuilder$1\2\408\1 = false;
                                var10_15 = $this$select_Ao2T0zE_u24lambda_u240\1;
                                $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = false;
                                <this>\3 = var10_15;
                                $this$select_Ao2T0zE_u24lambda_u240\1.getParams().put("select", CollectionsKt.listOf((Object)columns\1));
                                requestBuilder\1 = var7_10;
                                selectRequest\1 = new SelectRequest(requestBuilder\1.getHead(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), $this\1.getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)columns\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)selectRequest\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$select-Ao2T0zE\1\77;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute($this\1.getPostgrest(), $this\1.getTable(), (PostgrestRequest)selectRequest\1, (Continuation)this);
                                ** if (v0 != var14_2) goto lbl30
lbl29:
                                // 1 sources

                                return var14_2;
lbl30:
                                // 1 sources

                                ** GOTO lbl42
                            }
                            case 1: {
                                $i$f$select-Ao2T0zE\1\77 = this.I$0;
                                requestBuilder\1 = (SelectRequestBuilder)this.L$3;
                                selectRequest\1 = (SelectRequest)this.L$2;
                                columns\1 = (String)this.L$1;
                                $this\1 = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl42:
                                    // 2 sources

                                    this_\4 = (PostgrestResult)v0;
                                    $i$f$decodeList\4\77 = false;
                                    this_\5 = this_\4;
                                    $i$f$decodeAs\5\420 = false;
                                    selectRequest\1 = this_\5.getPostgrest().getSerializer();
                                    value\6 = this_\5.getData();
                                    $i$f$decode\6\421 = false;
                                    dtoList = (List)$this$decode\6.decode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrItemDto.class))), value\6);
                                    $this$map\7 = dtoList;
                                    $i$f$map\7\78 = false;
                                    $i$f$decodeAs\5\420 = $this$map\7;
                                    destination\8 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\7, (int)10));
                                    $i$f$mapTo\8\423 = false;
                                    for (T item\8 : $this$mapTo\8) {
                                        $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = (WrItemDto)item\8;
                                        var13_23 = destination\8;
                                        $i$a$-map-WildRiftSupabaseRepository$fetchAllItems$2$items$1\9\425\0 = false;
                                        var13_23.add(it\9.toModel());
                                    }
                                    items = (List)destination\8;
                                    var2_22 = Result.constructor-impl((Object)items);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error obteniendo \u00edtems de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_22 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_22);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<? extends List<WildRiftItem>>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var4_4) {
                    return var4_4;
                }
                ** GOTO lbl20
            }
            case 1: {
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl20:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object saveItem-gIAlu-s(@NotNull WildRiftItem item, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof saveItem.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.saveItem-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)item);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(item, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    int I$0;
                    int I$1;
                    int label;
                    final /* synthetic */ WildRiftItem $item;
                    {
                        this.$item = $item;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var25_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                dto = WrItemDto.Companion.fromModel(this.$item);
                                var3_4 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_items");
                                value\1 = dto;
                                $i$f$upsert\1\89 = 0;
                                var7_10 = $this\1;
                                values\2 = CollectionsKt.listOf((Object)value\1);
                                $i$f$upsert\2\407 = 0;
                                var11_17 = var10_16 = new UpsertRequestBuilder(((Postgrest.Config)this_\2.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-upsert-PostgrestQueryBuilder$upsert$5\10\408\0 = false;
                                <this>\10 = var11_17;
                                requestBuilder\2 = var10_16;
                                $this$encodeToJsonElement\3 = this_\2.getPostgrest().getSerializer();
                                $i$f$encodeToJsonElement\3\410 = false;
                                var16_23 = (Json)Json.Default;
                                $this$encode\4 /* !! */  = $this$encodeToJsonElement\3;
                                $i$f$encode\4\411 = false;
                                $this$encode\4 /* !! */  = $this$encode\4 /* !! */ .encode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrItemDto.class))), (Object)values\2);
                                $i$f$decodeFromString\5\411 = false;
                                this_\5.getSerializersModule();
                                body\2 = JsonElementKt.getJsonArray((JsonElement)((JsonElement)this_\5.decodeFromString((DeserializationStrategy)JsonElement.Companion.serializer(), (String)string\5)));
                                $this$map\6 = (Iterable)body\2;
                                $i$f$map\6\414 = false;
                                string\5 = $this$map\6;
                                destination\7 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\6, (int)10));
                                $i$f$mapTo\7\415 = false;
                                for (T item\7 : $this$mapTo\7) {
                                    var22_35 = (JsonElement)item\7;
                                    var23_36 = destination\7;
                                    $i$a$-map-PostgrestQueryBuilder$upsert$columns$1\8\417\2 = false;
                                    var23_36.add(JsonElementKt.getJsonObject((JsonElement)it\8).keySet());
                                }
                                columns\2 = CollectionsKt.distinct((Iterable)CollectionsKt.flatten((Iterable)((List)destination\7)));
                                if (((Collection)columns\2).isEmpty() == false) {
                                    requestBuilder\2.getParams().put("columns", CollectionsKt.listOf((Object)CollectionsKt.joinToString$default((Iterable)columns\2, (CharSequence)",", null, null, (int)0, null, null, (int)62, null)));
                                }
                                v0 = requestBuilder\2.getOnConflict();
                                if (v0 != null) {
                                    it\2 = v0;
                                    $i$a$-let-PostgrestQueryBuilder$upsert$3\9\420\2 = false;
                                    requestBuilder\2.getParams().put("on_conflict", CollectionsKt.listOf((Object)it\2));
                                }
                                var16_25 = requestBuilder\2.getReturning();
                                var17_26 = requestBuilder\2.getCount();
                                var18_30 = UtilsKt.mapToFirstValue((Map)requestBuilder\2.getParams());
                                var19_31 = requestBuilder\2.getDefaultToNull();
                                var20_33 = requestBuilder\2.getIgnoreDuplicates();
                                var21_34 = this_\2.getSchema();
                                var22_35 = requestBuilder\2.getHeaders().build();
                                insertRequest\2 = new InsertRequest(true, var16_25, var17_26, var20_33 != false, var19_31 != false, body\2, var18_30, var21_34, (Headers)var22_35);
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)dto);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)value\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)this_\2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable((Object)values\2);
                                this.L$5 = SpillingKt.nullOutSpilledVariable((Object)body\2);
                                this.L$6 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\2);
                                this.L$7 = SpillingKt.nullOutSpilledVariable((Object)columns\2);
                                this.L$8 = SpillingKt.nullOutSpilledVariable((Object)insertRequest\2);
                                this.I$0 = $i$f$upsert\1\89;
                                this.I$1 = $i$f$upsert\2\407;
                                this.label = 1;
                                v1 = RestRequestExecutor.INSTANCE.execute(this_\2.getPostgrest(), this_\2.getTable(), (PostgrestRequest)insertRequest\2, (Continuation)this);
                                ** if (v1 != var25_2) goto lbl77
lbl76:
                                // 1 sources

                                return var25_2;
lbl77:
                                // 1 sources

                                ** GOTO lbl96
                            }
                            case 1: {
                                $i$f$upsert\2\407 = this.I$1;
                                $i$f$upsert\1\89 = this.I$0;
                                insertRequest\2 = (InsertRequest)this.L$8;
                                columns\2 = (List)this.L$7;
                                requestBuilder\2 = (UpsertRequestBuilder)this.L$6;
                                body\2 = (JsonArray)this.L$5;
                                values\2 = (List)this.L$4;
                                this_\2 = (PostgrestQueryBuilder)this.L$3;
                                value\1 = (WrItemDto)this.L$2;
                                $this\1 = (PostgrestQueryBuilder)this.L$1;
                                dto = (WrItemDto)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v1 = $result;
lbl96:
                                    // 2 sources

                                    var5_38 = current = CollectionsKt.toMutableList((Collection)WildRiftRepository.INSTANCE.getItems());
                                    var6_9 = this.$item;
                                    $i$f$indexOfFirst\11\93 = false;
                                    index\11 = 0;
                                    for (E item\11 : $this$indexOfFirst\11) {
                                        it\12 = (WildRiftItem)item\11;
                                        $i$a$-indexOfFirst-WildRiftSupabaseRepository$saveItem$2$index$1\12\437\0 = false;
                                        if (!Intrinsics.areEqual((Object)it\12.getId(), (Object)var6_9.getId())) ** GOTO lbl106
                                        v2 = index\11;
                                        ** GOTO lbl109
lbl106:
                                        // 1 sources

                                        ++index\11;
                                    }
                                    v2 = index = -1;
lbl109:
                                    // 2 sources

                                    if (index != -1) {
                                        current.set(index, this.$item);
                                    } else {
                                        current.add(0, this.$item);
                                    }
                                    WildRiftRepository.INSTANCE.setItems(current);
                                    var2_3 = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error guardando \u00edtem en Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_3 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                item = (WildRiftItem)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object deleteItem-gIAlu-s(@NotNull String itemId, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof deleteItem.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.deleteItem-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)itemId);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(itemId, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int I$0;
                    int label;
                    final /* synthetic */ String $itemId;
                    {
                        this.$itemId = $itemId;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var15_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                var2_3 /* !! */  = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_items");
                                var3_4 = this.$itemId;
                                $i$f$delete\1\109 = 0;
                                $this$invokeSuspend_u24lambda_u241\2 = var5_7 = new PostgrestRequestBuilder(((Postgrest.Config)this_\1 /* !! */ .getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-delete-WildRiftSupabaseRepository$deleteItem$2$1\2\404\0 = false;
                                this_\3 = $this$invokeSuspend_u24lambda_u241\2;
                                $i$f$filter\3\110 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4 = filter\3 = new PostgrestFilterBuilder(this_\3.getPropertyConversionMethod(), this_\3.getParams(), false, 4, null);
                                $i$a$-filter-WildRiftSupabaseRepository$deleteItem$2$1$1\4\406\2 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4.eq("id", (Object)var3_4);
                                requestBuilder\1 = var5_7;
                                deleteRequest\1 = new DeleteRequest(requestBuilder\1.getReturning(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), this_\1 /* !! */ .getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)this_\1 /* !! */ );
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)deleteRequest\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$delete\1\109;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute(this_\1 /* !! */ .getPostgrest(), this_\1 /* !! */ .getTable(), (PostgrestRequest)deleteRequest\1, (Continuation)this);
                                ** if (v0 != var15_2) goto lbl29
lbl28:
                                // 1 sources

                                return var15_2;
lbl29:
                                // 1 sources

                                ** GOTO lbl40
                            }
                            case 1: {
                                $i$f$delete\1\109 = this.I$0;
                                requestBuilder\1 = (PostgrestRequestBuilder)this.L$2;
                                deleteRequest\1 = (DeleteRequest)this.L$1;
                                this_\1 /* !! */  = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl40:
                                    // 2 sources

                                    this_\1 /* !! */  = WildRiftRepository.INSTANCE.getItems();
                                    var3_4 = this.$itemId;
                                    var14_18 = WildRiftRepository.INSTANCE;
                                    $i$f$filterNot\5\112 = false;
                                    deleteRequest\1 = $this$filterNot\5;
                                    destination\6 = new ArrayList<E>();
                                    $i$f$filterNotTo\6\416 = false;
                                    for (T element\6 : $this$filterNotTo\6) {
                                        it\7 = (WildRiftItem)element\6;
                                        $i$a$-filterNot-WildRiftSupabaseRepository$deleteItem$2$2\7\417\0 = false;
                                        if (Intrinsics.areEqual((Object)it\7.getId(), (Object)var3_4)) continue;
                                        destination\6.add(element\6);
                                    }
                                    var14_18.setItems((List)destination\6);
                                    var2_3 /* !! */  = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error eliminando \u00edtem de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_3 /* !! */  = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3 /* !! */ );
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                itemId = (String)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object fetchAllChampions-IoAF18A(@NotNull Continuation<? super Result<? extends List<Champion>>> $completion) {
        if (!($completion instanceof fetchAllChampions.1)) ** GOTO lbl-1000
        var3_2 = $completion;
        if ((var3_2.label & -2147483648) != 0) {
            var3_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.fetchAllChampions-IoAF18A((Continuation<? super Result<? extends List<Champion>>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var4_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends List<? extends Champion>>>, Object>(null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int I$0;
                    int label;

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var15_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                $this\1 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_champions");
                                columns\1 = Columns.Companion.getALL-U9NzzuM();
                                $i$f$select-Ao2T0zE\1\126 = 0;
                                $this$select_Ao2T0zE_u24lambda_u240\1 = var7_9 = new SelectRequestBuilder(((Postgrest.Config)$this\1.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-apply-PostgrestQueryBuilder$select$requestBuilder$1\2\408\1 = false;
                                var10_12 = $this$select_Ao2T0zE_u24lambda_u240\1;
                                $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = false;
                                <this>\3 = var10_12;
                                $this$select_Ao2T0zE_u24lambda_u240\1.getParams().put("select", CollectionsKt.listOf((Object)columns\1));
                                requestBuilder\1 /* !! */  = var7_9;
                                selectRequest\1 = new SelectRequest(requestBuilder\1 /* !! */ .getHead(), requestBuilder\1 /* !! */ .getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1 /* !! */ .getParams()), $this\1.getSchema(), requestBuilder\1 /* !! */ .getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)columns\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)selectRequest\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1 /* !! */ );
                                this.I$0 = $i$f$select-Ao2T0zE\1\126;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute($this\1.getPostgrest(), $this\1.getTable(), (PostgrestRequest)selectRequest\1, (Continuation)this);
                                ** if (v0 != var15_2) goto lbl30
lbl29:
                                // 1 sources

                                return var15_2;
lbl30:
                                // 1 sources

                                ** GOTO lbl42
                            }
                            case 1: {
                                $i$f$select-Ao2T0zE\1\126 = this.I$0;
                                requestBuilder\1 /* !! */  = (SelectRequestBuilder)this.L$3;
                                selectRequest\1 = (SelectRequest)this.L$2;
                                columns\1 = (String)this.L$1;
                                $this\1 = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl42:
                                    // 2 sources

                                    this_\4 = (PostgrestResult)v0;
                                    $i$f$decodeList\4\126 = false;
                                    this_\5 = this_\4;
                                    $i$f$decodeAs\5\420 = false;
                                    selectRequest\1 = this_\5.getPostgrest().getSerializer();
                                    value\6 = this_\5.getData();
                                    $i$f$decode\6\421 = false;
                                    dtoList = (List)$this$decode\6.decode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrChampionDto.class))), value\6);
                                    $this$associateBy\7 = WildRiftRepository.INSTANCE.getChampions();
                                    $i$f$associateBy\7\127 = false;
                                    capacity\7 = RangesKt.coerceAtLeast((int)MapsKt.mapCapacity((int)CollectionsKt.collectionSizeOrDefault((Iterable)$this$associateBy\7, (int)10)), (int)16);
                                    $this$decode\6 = $this$associateBy\7;
                                    destination\8 = new LinkedHashMap<K, V>(capacity\7);
                                    $i$f$associateByTo\8\424 = false;
                                    for (E element\8 : $this$associateByTo\8) {
                                        requestBuilder\1 /* !! */  = (Champion)element\8;
                                        var14_22 = destination\8;
                                        $i$a$-associateBy-WildRiftSupabaseRepository$fetchAllChampions$2$fallbackMap$1\9\426\0 = false;
                                        var14_22.put(it\9.getId(), element\8);
                                    }
                                    fallbackMap = destination\8;
                                    $this$map\10 = dtoList;
                                    $i$f$map\10\128 = false;
                                    $this$associateByTo\8 = $this$map\10;
                                    destination\11 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\10, (int)10));
                                    $i$f$mapTo\11\429 = false;
                                    for (E item\11 : $this$mapTo\11) {
                                        it\9 = (WrChampionDto)item\11;
                                        var14_22 = destination\11;
                                        $i$a$-map-WildRiftSupabaseRepository$fetchAllChampions$2$champions$1\12\431\0 = false;
                                        var14_22.add(dto\12.toModel((Champion)fallbackMap.get(dto\12.getId())));
                                    }
                                    champions = (List)destination\11;
                                    var2_20 = Result.constructor-impl((Object)champions);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error obteniendo campeones de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_20 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_20);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<? extends List<Champion>>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var4_4) {
                    return var4_4;
                }
                ** GOTO lbl20
            }
            case 1: {
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl20:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object saveChampion-gIAlu-s(@NotNull Champion champion, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof saveChampion.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.saveChampion-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)champion);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(champion, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    int I$0;
                    int I$1;
                    int label;
                    final /* synthetic */ Champion $champion;
                    {
                        this.$champion = $champion;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var25_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                dto = WrChampionDto.Companion.fromModel(this.$champion);
                                var3_4 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_champions");
                                value\1 = dto;
                                $i$f$upsert\1\141 = 0;
                                var7_10 = $this\1;
                                values\2 = CollectionsKt.listOf((Object)value\1);
                                $i$f$upsert\2\407 = 0;
                                var11_17 = var10_16 = new UpsertRequestBuilder(((Postgrest.Config)this_\2.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-upsert-PostgrestQueryBuilder$upsert$5\10\408\0 = false;
                                <this>\10 = var11_17;
                                requestBuilder\2 = var10_16;
                                $this$encodeToJsonElement\3 = this_\2.getPostgrest().getSerializer();
                                $i$f$encodeToJsonElement\3\410 = false;
                                var16_23 = (Json)Json.Default;
                                $this$encode\4 /* !! */  = $this$encodeToJsonElement\3;
                                $i$f$encode\4\411 = false;
                                $this$encode\4 /* !! */  = $this$encode\4 /* !! */ .encode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrChampionDto.class))), (Object)values\2);
                                $i$f$decodeFromString\5\411 = false;
                                this_\5.getSerializersModule();
                                body\2 = JsonElementKt.getJsonArray((JsonElement)((JsonElement)this_\5.decodeFromString((DeserializationStrategy)JsonElement.Companion.serializer(), (String)string\5)));
                                $this$map\6 = (Iterable)body\2;
                                $i$f$map\6\414 = false;
                                string\5 = $this$map\6;
                                destination\7 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\6, (int)10));
                                $i$f$mapTo\7\415 = false;
                                for (T item\7 : $this$mapTo\7) {
                                    var22_35 = (JsonElement)item\7;
                                    var23_36 = destination\7;
                                    $i$a$-map-PostgrestQueryBuilder$upsert$columns$1\8\417\2 = false;
                                    var23_36.add(JsonElementKt.getJsonObject((JsonElement)it\8).keySet());
                                }
                                columns\2 = CollectionsKt.distinct((Iterable)CollectionsKt.flatten((Iterable)((List)destination\7)));
                                if (((Collection)columns\2).isEmpty() == false) {
                                    requestBuilder\2.getParams().put("columns", CollectionsKt.listOf((Object)CollectionsKt.joinToString$default((Iterable)columns\2, (CharSequence)",", null, null, (int)0, null, null, (int)62, null)));
                                }
                                v0 = requestBuilder\2.getOnConflict();
                                if (v0 != null) {
                                    it\2 = v0;
                                    $i$a$-let-PostgrestQueryBuilder$upsert$3\9\420\2 = false;
                                    requestBuilder\2.getParams().put("on_conflict", CollectionsKt.listOf((Object)it\2));
                                }
                                var16_25 = requestBuilder\2.getReturning();
                                var17_26 = requestBuilder\2.getCount();
                                var18_30 = UtilsKt.mapToFirstValue((Map)requestBuilder\2.getParams());
                                var19_31 = requestBuilder\2.getDefaultToNull();
                                var20_33 = requestBuilder\2.getIgnoreDuplicates();
                                var21_34 = this_\2.getSchema();
                                var22_35 = requestBuilder\2.getHeaders().build();
                                insertRequest\2 = new InsertRequest(true, var16_25, var17_26, var20_33 != false, var19_31 != false, body\2, var18_30, var21_34, (Headers)var22_35);
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)dto);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)value\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)this_\2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable((Object)values\2);
                                this.L$5 = SpillingKt.nullOutSpilledVariable((Object)body\2);
                                this.L$6 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\2);
                                this.L$7 = SpillingKt.nullOutSpilledVariable((Object)columns\2);
                                this.L$8 = SpillingKt.nullOutSpilledVariable((Object)insertRequest\2);
                                this.I$0 = $i$f$upsert\1\141;
                                this.I$1 = $i$f$upsert\2\407;
                                this.label = 1;
                                v1 = RestRequestExecutor.INSTANCE.execute(this_\2.getPostgrest(), this_\2.getTable(), (PostgrestRequest)insertRequest\2, (Continuation)this);
                                ** if (v1 != var25_2) goto lbl77
lbl76:
                                // 1 sources

                                return var25_2;
lbl77:
                                // 1 sources

                                ** GOTO lbl96
                            }
                            case 1: {
                                $i$f$upsert\2\407 = this.I$1;
                                $i$f$upsert\1\141 = this.I$0;
                                insertRequest\2 = (InsertRequest)this.L$8;
                                columns\2 = (List)this.L$7;
                                requestBuilder\2 = (UpsertRequestBuilder)this.L$6;
                                body\2 = (JsonArray)this.L$5;
                                values\2 = (List)this.L$4;
                                this_\2 = (PostgrestQueryBuilder)this.L$3;
                                value\1 = (WrChampionDto)this.L$2;
                                $this\1 = (PostgrestQueryBuilder)this.L$1;
                                dto = (WrChampionDto)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v1 = $result;
lbl96:
                                    // 2 sources

                                    var5_38 = current = CollectionsKt.toMutableList((Collection)WildRiftRepository.INSTANCE.getChampions());
                                    var6_9 = this.$champion;
                                    $i$f$indexOfFirst\11\144 = false;
                                    index\11 = 0;
                                    for (E item\11 : $this$indexOfFirst\11) {
                                        it\12 = (Champion)item\11;
                                        $i$a$-indexOfFirst-WildRiftSupabaseRepository$saveChampion$2$index$1\12\437\0 = false;
                                        if (!Intrinsics.areEqual((Object)it\12.getId(), (Object)var6_9.getId())) ** GOTO lbl106
                                        v2 = index\11;
                                        ** GOTO lbl109
lbl106:
                                        // 1 sources

                                        ++index\11;
                                    }
                                    v2 = index = -1;
lbl109:
                                    // 2 sources

                                    if (index != -1) {
                                        current.set(index, this.$champion);
                                    } else {
                                        current.add(0, this.$champion);
                                    }
                                    WildRiftRepository.INSTANCE.setChampions(current);
                                    var2_3 = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error guardando campe\u00f3n en Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_3 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                champion = (Champion)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object deleteChampion-gIAlu-s(@NotNull String championId, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof deleteChampion.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.deleteChampion-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)championId);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(championId, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int I$0;
                    int label;
                    final /* synthetic */ String $championId;
                    {
                        this.$championId = $championId;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var15_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                var2_3 /* !! */  = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_champions");
                                var3_4 = this.$championId;
                                $i$f$delete\1\160 = 0;
                                $this$invokeSuspend_u24lambda_u241\2 = var5_7 = new PostgrestRequestBuilder(((Postgrest.Config)this_\1 /* !! */ .getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-delete-WildRiftSupabaseRepository$deleteChampion$2$1\2\404\0 = false;
                                this_\3 = $this$invokeSuspend_u24lambda_u241\2;
                                $i$f$filter\3\161 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4 = filter\3 = new PostgrestFilterBuilder(this_\3.getPropertyConversionMethod(), this_\3.getParams(), false, 4, null);
                                $i$a$-filter-WildRiftSupabaseRepository$deleteChampion$2$1$1\4\406\2 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4.eq("id", (Object)var3_4);
                                requestBuilder\1 = var5_7;
                                deleteRequest\1 = new DeleteRequest(requestBuilder\1.getReturning(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), this_\1 /* !! */ .getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)this_\1 /* !! */ );
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)deleteRequest\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$delete\1\160;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute(this_\1 /* !! */ .getPostgrest(), this_\1 /* !! */ .getTable(), (PostgrestRequest)deleteRequest\1, (Continuation)this);
                                ** if (v0 != var15_2) goto lbl29
lbl28:
                                // 1 sources

                                return var15_2;
lbl29:
                                // 1 sources

                                ** GOTO lbl40
                            }
                            case 1: {
                                $i$f$delete\1\160 = this.I$0;
                                requestBuilder\1 = (PostgrestRequestBuilder)this.L$2;
                                deleteRequest\1 = (DeleteRequest)this.L$1;
                                this_\1 /* !! */  = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl40:
                                    // 2 sources

                                    this_\1 /* !! */  = WildRiftRepository.INSTANCE.getChampions();
                                    var3_4 = this.$championId;
                                    var14_18 = WildRiftRepository.INSTANCE;
                                    $i$f$filterNot\5\163 = false;
                                    deleteRequest\1 = $this$filterNot\5;
                                    destination\6 = new ArrayList<E>();
                                    $i$f$filterNotTo\6\416 = false;
                                    for (T element\6 : $this$filterNotTo\6) {
                                        it\7 = (Champion)element\6;
                                        $i$a$-filterNot-WildRiftSupabaseRepository$deleteChampion$2$2\7\417\0 = false;
                                        if (Intrinsics.areEqual((Object)it\7.getId(), (Object)var3_4)) continue;
                                        destination\6.add(element\6);
                                    }
                                    var14_18.setChampions((List)destination\6);
                                    var2_3 /* !! */  = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error eliminando campe\u00f3n de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_3 /* !! */  = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3 /* !! */ );
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                championId = (String)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object saveAllChampionsToSupabase-gIAlu-s(@NotNull List<Champion> champions, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof saveAllChampionsToSupabase.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.saveAllChampionsToSupabase-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)champions);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>((List<Champion>)champions, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    Object L$9;
                    Object L$10;
                    Object L$11;
                    int I$0;
                    int I$1;
                    int I$2;
                    int label;
                    final /* synthetic */ List<Champion> $champions;
                    {
                        this.$champions = $champions;
                        super(2, $completion);
                    }

                    /*
                     * Exception decompiling
                     */
                    public final Object invokeSuspend(Object $result) {
                        /*
                         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                         * 
                         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 8[WHILELOOP]
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
                         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
                         *     at org.benf.cfr.reader.entities.Method.dump(Method.java:598)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.ClassFileDumperAnonymousInner.dumpWithArgs(ClassFileDumperAnonymousInner.java:87)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.ConstructorInvokationAnonymousInner.dumpInner(ConstructorInvokationAnonymousInner.java:82)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression.dumpInner(CastExpression.java:114)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:139)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression.dumpInner(CastExpression.java:114)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dump(AbstractExpression.java:98)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation.dumpInner(StaticFunctionInvokation.java:143)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dump(AbstractExpression.java:98)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment.dump(StructuredAssignment.java:69)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredCase.dump(StructuredCase.java:94)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredSwitch.dump(StructuredSwitch.java:59)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.dump(AttributeCode.java:135)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.entities.Method.dump(Method.java:627)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.AbstractClassFileDumper.dumpMethods(AbstractClassFileDumper.java:211)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.ClassFileDumperNormal.dump(ClassFileDumperNormal.java:70)
                         *     at org.benf.cfr.reader.entities.ClassFile.dump(ClassFile.java:1167)
                         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:952)
                         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
                         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
                         *     at org.benf.cfr.reader.Main.main(Main.java:54)
                         */
                        throw new IllegalStateException("Decompilation failed");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                champions = (List)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object fetchAllRunes-IoAF18A(@NotNull Continuation<? super Result<? extends List<RuneItem>>> $completion) {
        if (!($completion instanceof fetchAllRunes.1)) ** GOTO lbl-1000
        var3_2 = $completion;
        if ((var3_2.label & -2147483648) != 0) {
            var3_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.fetchAllRunes-IoAF18A((Continuation<? super Result<? extends List<RuneItem>>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var4_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends List<? extends RuneItem>>>, Object>(null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int I$0;
                    int label;

                    /*
                     * Unable to fully structure code
                     */
                    public final Object invokeSuspend(Object $result) {
                        var14_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                $this\1 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_runes");
                                columns\1 = Columns.Companion.getALL-U9NzzuM();
                                $i$f$select-Ao2T0zE\1\190 = 0;
                                $this$select_Ao2T0zE_u24lambda_u240\1 = var7_10 = new SelectRequestBuilder(((Postgrest.Config)$this\1.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-apply-PostgrestQueryBuilder$select$requestBuilder$1\2\408\1 = false;
                                var10_15 = $this$select_Ao2T0zE_u24lambda_u240\1;
                                $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = false;
                                <this>\3 = var10_15;
                                $this$select_Ao2T0zE_u24lambda_u240\1.getParams().put("select", CollectionsKt.listOf((Object)columns\1));
                                requestBuilder\1 = var7_10;
                                selectRequest\1 = new SelectRequest(requestBuilder\1.getHead(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), $this\1.getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)columns\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)selectRequest\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$select-Ao2T0zE\1\190;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute($this\1.getPostgrest(), $this\1.getTable(), (PostgrestRequest)selectRequest\1, (Continuation)this);
                                ** if (v0 != var14_2) goto lbl30
lbl29:
                                // 1 sources

                                return var14_2;
lbl30:
                                // 1 sources

                                ** GOTO lbl42
                            }
                            case 1: {
                                $i$f$select-Ao2T0zE\1\190 = this.I$0;
                                requestBuilder\1 = (SelectRequestBuilder)this.L$3;
                                selectRequest\1 = (SelectRequest)this.L$2;
                                columns\1 = (String)this.L$1;
                                $this\1 = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl42:
                                    // 2 sources

                                    this_\4 = (PostgrestResult)v0;
                                    $i$f$decodeList\4\190 = false;
                                    this_\5 = this_\4;
                                    $i$f$decodeAs\5\420 = false;
                                    selectRequest\1 = this_\5.getPostgrest().getSerializer();
                                    value\6 = this_\5.getData();
                                    $i$f$decode\6\421 = false;
                                    dtoList = (List)$this$decode\6.decode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrRuneDto.class))), value\6);
                                    $this$map\7 = dtoList;
                                    $i$f$map\7\191 = false;
                                    $i$f$decodeAs\5\420 = $this$map\7;
                                    destination\8 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\7, (int)10));
                                    $i$f$mapTo\8\423 = false;
                                    for (T item\8 : $this$mapTo\8) {
                                        $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = (WrRuneDto)item\8;
                                        var13_23 = destination\8;
                                        $i$a$-map-WildRiftSupabaseRepository$fetchAllRunes$2$1\9\425\0 = false;
                                        var13_23.add(it\9.toModel());
                                    }
                                    var2_22 = Result.constructor-impl((Object)((List)destination\8));
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error obteniendo runas de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_22 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_22);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<? extends List<RuneItem>>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var4_4) {
                    return var4_4;
                }
                ** GOTO lbl20
            }
            case 1: {
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl20:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object saveRune-gIAlu-s(@NotNull RuneItem rune, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof saveRune.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.saveRune-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)rune);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(rune, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    int I$0;
                    int I$1;
                    int label;
                    final /* synthetic */ RuneItem $rune;
                    {
                        this.$rune = $rune;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var25_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                dto = WrRuneDto.Companion.fromModel(this.$rune);
                                var3_4 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_runes");
                                value\1 = dto;
                                $i$f$upsert\1\201 = 0;
                                var7_10 = $this\1;
                                values\2 = CollectionsKt.listOf((Object)value\1);
                                $i$f$upsert\2\407 = 0;
                                var11_17 = var10_16 = new UpsertRequestBuilder(((Postgrest.Config)this_\2.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-upsert-PostgrestQueryBuilder$upsert$5\10\408\0 = false;
                                <this>\10 = var11_17;
                                requestBuilder\2 = var10_16;
                                $this$encodeToJsonElement\3 = this_\2.getPostgrest().getSerializer();
                                $i$f$encodeToJsonElement\3\410 = false;
                                var16_23 = (Json)Json.Default;
                                $this$encode\4 /* !! */  = $this$encodeToJsonElement\3;
                                $i$f$encode\4\411 = false;
                                $this$encode\4 /* !! */  = $this$encode\4 /* !! */ .encode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrRuneDto.class))), (Object)values\2);
                                $i$f$decodeFromString\5\411 = false;
                                this_\5.getSerializersModule();
                                body\2 = JsonElementKt.getJsonArray((JsonElement)((JsonElement)this_\5.decodeFromString((DeserializationStrategy)JsonElement.Companion.serializer(), (String)string\5)));
                                $this$map\6 = (Iterable)body\2;
                                $i$f$map\6\414 = false;
                                string\5 = $this$map\6;
                                destination\7 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\6, (int)10));
                                $i$f$mapTo\7\415 = false;
                                for (T item\7 : $this$mapTo\7) {
                                    var22_35 = (JsonElement)item\7;
                                    var23_36 = destination\7;
                                    $i$a$-map-PostgrestQueryBuilder$upsert$columns$1\8\417\2 = false;
                                    var23_36.add(JsonElementKt.getJsonObject((JsonElement)it\8).keySet());
                                }
                                columns\2 = CollectionsKt.distinct((Iterable)CollectionsKt.flatten((Iterable)((List)destination\7)));
                                if (((Collection)columns\2).isEmpty() == false) {
                                    requestBuilder\2.getParams().put("columns", CollectionsKt.listOf((Object)CollectionsKt.joinToString$default((Iterable)columns\2, (CharSequence)",", null, null, (int)0, null, null, (int)62, null)));
                                }
                                v0 = requestBuilder\2.getOnConflict();
                                if (v0 != null) {
                                    it\2 = v0;
                                    $i$a$-let-PostgrestQueryBuilder$upsert$3\9\420\2 = false;
                                    requestBuilder\2.getParams().put("on_conflict", CollectionsKt.listOf((Object)it\2));
                                }
                                var16_25 = requestBuilder\2.getReturning();
                                var17_26 = requestBuilder\2.getCount();
                                var18_30 = UtilsKt.mapToFirstValue((Map)requestBuilder\2.getParams());
                                var19_31 = requestBuilder\2.getDefaultToNull();
                                var20_33 = requestBuilder\2.getIgnoreDuplicates();
                                var21_34 = this_\2.getSchema();
                                var22_35 = requestBuilder\2.getHeaders().build();
                                insertRequest\2 = new InsertRequest(true, var16_25, var17_26, var20_33 != false, var19_31 != false, body\2, var18_30, var21_34, (Headers)var22_35);
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)dto);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)value\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)this_\2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable((Object)values\2);
                                this.L$5 = SpillingKt.nullOutSpilledVariable((Object)body\2);
                                this.L$6 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\2);
                                this.L$7 = SpillingKt.nullOutSpilledVariable((Object)columns\2);
                                this.L$8 = SpillingKt.nullOutSpilledVariable((Object)insertRequest\2);
                                this.I$0 = $i$f$upsert\1\201;
                                this.I$1 = $i$f$upsert\2\407;
                                this.label = 1;
                                v1 = RestRequestExecutor.INSTANCE.execute(this_\2.getPostgrest(), this_\2.getTable(), (PostgrestRequest)insertRequest\2, (Continuation)this);
                                ** if (v1 != var25_2) goto lbl77
lbl76:
                                // 1 sources

                                return var25_2;
lbl77:
                                // 1 sources

                                ** GOTO lbl96
                            }
                            case 1: {
                                $i$f$upsert\2\407 = this.I$1;
                                $i$f$upsert\1\201 = this.I$0;
                                insertRequest\2 = (InsertRequest)this.L$8;
                                columns\2 = (List)this.L$7;
                                requestBuilder\2 = (UpsertRequestBuilder)this.L$6;
                                body\2 = (JsonArray)this.L$5;
                                values\2 = (List)this.L$4;
                                this_\2 = (PostgrestQueryBuilder)this.L$3;
                                value\1 = (WrRuneDto)this.L$2;
                                $this\1 = (PostgrestQueryBuilder)this.L$1;
                                dto = (WrRuneDto)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v1 = $result;
lbl96:
                                    // 2 sources

                                    var5_38 = current = CollectionsKt.toMutableList((Collection)WildRiftRepository.INSTANCE.getRunes());
                                    var6_9 = this.$rune;
                                    $i$f$indexOfFirst\11\203 = false;
                                    index\11 = 0;
                                    for (E item\11 : $this$indexOfFirst\11) {
                                        it\12 = (RuneItem)item\11;
                                        $i$a$-indexOfFirst-WildRiftSupabaseRepository$saveRune$2$index$1\12\437\0 = false;
                                        if (!Intrinsics.areEqual((Object)it\12.getId(), (Object)var6_9.getId())) ** GOTO lbl106
                                        v2 = index\11;
                                        ** GOTO lbl109
lbl106:
                                        // 1 sources

                                        ++index\11;
                                    }
                                    v2 = index = -1;
lbl109:
                                    // 2 sources

                                    if (index != -1) {
                                        current.set(index, this.$rune);
                                    } else {
                                        Boxing.boxBoolean((boolean)current.add(this.$rune));
                                    }
                                    WildRiftRepository.INSTANCE.setRunes(current);
                                    var2_3 = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    var2_3 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                rune = (RuneItem)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object deleteRune-gIAlu-s(@NotNull String runeId, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof deleteRune.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.deleteRune-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)runeId);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(runeId, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int I$0;
                    int label;
                    final /* synthetic */ String $runeId;
                    {
                        this.$runeId = $runeId;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var15_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                var2_3 /* !! */  = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_runes");
                                var3_4 = this.$runeId;
                                $i$f$delete\1\214 = 0;
                                $this$invokeSuspend_u24lambda_u241\2 = var5_7 = new PostgrestRequestBuilder(((Postgrest.Config)this_\1 /* !! */ .getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-delete-WildRiftSupabaseRepository$deleteRune$2$1\2\404\0 = false;
                                this_\3 = $this$invokeSuspend_u24lambda_u241\2;
                                $i$f$filter\3\214 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4 = filter\3 = new PostgrestFilterBuilder(this_\3.getPropertyConversionMethod(), this_\3.getParams(), false, 4, null);
                                $i$a$-filter-WildRiftSupabaseRepository$deleteRune$2$1$1\4\406\2 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4.eq("id", (Object)var3_4);
                                requestBuilder\1 = var5_7;
                                deleteRequest\1 = new DeleteRequest(requestBuilder\1.getReturning(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), this_\1 /* !! */ .getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)this_\1 /* !! */ );
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)deleteRequest\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$delete\1\214;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute(this_\1 /* !! */ .getPostgrest(), this_\1 /* !! */ .getTable(), (PostgrestRequest)deleteRequest\1, (Continuation)this);
                                ** if (v0 != var15_2) goto lbl29
lbl28:
                                // 1 sources

                                return var15_2;
lbl29:
                                // 1 sources

                                ** GOTO lbl40
                            }
                            case 1: {
                                $i$f$delete\1\214 = this.I$0;
                                requestBuilder\1 = (PostgrestRequestBuilder)this.L$2;
                                deleteRequest\1 = (DeleteRequest)this.L$1;
                                this_\1 /* !! */  = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl40:
                                    // 2 sources

                                    this_\1 /* !! */  = WildRiftRepository.INSTANCE.getRunes();
                                    var3_4 = this.$runeId;
                                    var14_18 = WildRiftRepository.INSTANCE;
                                    $i$f$filterNot\5\215 = false;
                                    deleteRequest\1 = $this$filterNot\5;
                                    destination\6 = new ArrayList<E>();
                                    $i$f$filterNotTo\6\416 = false;
                                    for (T element\6 : $this$filterNotTo\6) {
                                        it\7 = (RuneItem)element\6;
                                        $i$a$-filterNot-WildRiftSupabaseRepository$deleteRune$2$2\7\417\0 = false;
                                        if (Intrinsics.areEqual((Object)it\7.getId(), (Object)var3_4)) continue;
                                        destination\6.add(element\6);
                                    }
                                    var14_18.setRunes((List)destination\6);
                                    var2_3 /* !! */  = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    var2_3 /* !! */  = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3 /* !! */ );
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                runeId = (String)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object fetchAllSpells-IoAF18A(@NotNull Continuation<? super Result<? extends List<SummonerSpellItem>>> $completion) {
        if (!($completion instanceof fetchAllSpells.1)) ** GOTO lbl-1000
        var3_2 = $completion;
        if ((var3_2.label & -2147483648) != 0) {
            var3_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.fetchAllSpells-IoAF18A((Continuation<? super Result<? extends List<SummonerSpellItem>>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var4_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends List<? extends SummonerSpellItem>>>, Object>(null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int I$0;
                    int label;

                    /*
                     * Unable to fully structure code
                     */
                    public final Object invokeSuspend(Object $result) {
                        var14_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                $this\1 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_spells");
                                columns\1 = Columns.Companion.getALL-U9NzzuM();
                                $i$f$select-Ao2T0zE\1\224 = 0;
                                $this$select_Ao2T0zE_u24lambda_u240\1 = var7_10 = new SelectRequestBuilder(((Postgrest.Config)$this\1.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-apply-PostgrestQueryBuilder$select$requestBuilder$1\2\408\1 = false;
                                var10_15 = $this$select_Ao2T0zE_u24lambda_u240\1;
                                $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = false;
                                <this>\3 = var10_15;
                                $this$select_Ao2T0zE_u24lambda_u240\1.getParams().put("select", CollectionsKt.listOf((Object)columns\1));
                                requestBuilder\1 = var7_10;
                                selectRequest\1 = new SelectRequest(requestBuilder\1.getHead(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), $this\1.getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)columns\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)selectRequest\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$select-Ao2T0zE\1\224;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute($this\1.getPostgrest(), $this\1.getTable(), (PostgrestRequest)selectRequest\1, (Continuation)this);
                                ** if (v0 != var14_2) goto lbl30
lbl29:
                                // 1 sources

                                return var14_2;
lbl30:
                                // 1 sources

                                ** GOTO lbl42
                            }
                            case 1: {
                                $i$f$select-Ao2T0zE\1\224 = this.I$0;
                                requestBuilder\1 = (SelectRequestBuilder)this.L$3;
                                selectRequest\1 = (SelectRequest)this.L$2;
                                columns\1 = (String)this.L$1;
                                $this\1 = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl42:
                                    // 2 sources

                                    this_\4 = (PostgrestResult)v0;
                                    $i$f$decodeList\4\224 = false;
                                    this_\5 = this_\4;
                                    $i$f$decodeAs\5\420 = false;
                                    selectRequest\1 = this_\5.getPostgrest().getSerializer();
                                    value\6 = this_\5.getData();
                                    $i$f$decode\6\421 = false;
                                    dtoList = (List)$this$decode\6.decode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrSpellDto.class))), value\6);
                                    $this$map\7 = dtoList;
                                    $i$f$map\7\225 = false;
                                    $i$f$decodeAs\5\420 = $this$map\7;
                                    destination\8 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\7, (int)10));
                                    $i$f$mapTo\8\423 = false;
                                    for (T item\8 : $this$mapTo\8) {
                                        $i$a$-select-Ao2T0zE-PostgrestQueryBuilder$select$2\3\409\0 = (WrSpellDto)item\8;
                                        var13_23 = destination\8;
                                        $i$a$-map-WildRiftSupabaseRepository$fetchAllSpells$2$1\9\425\0 = false;
                                        var13_23.add(it\9.toModel());
                                    }
                                    var2_22 = Result.constructor-impl((Object)((List)destination\8));
                                }
                                catch (Exception e) {
                                    Log.e((String)"WildRiftSupabaseRepo", (String)("Error obteniendo hechizos de Supabase: " + e.getMessage()), (Throwable)e);
                                    var2_22 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_22);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<? extends List<SummonerSpellItem>>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var4_4) {
                    return var4_4;
                }
                ** GOTO lbl20
            }
            case 1: {
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl20:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object saveSpell-gIAlu-s(@NotNull SummonerSpellItem spell, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof saveSpell.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.saveSpell-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)spell);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(spell, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    int I$0;
                    int I$1;
                    int label;
                    final /* synthetic */ SummonerSpellItem $spell;
                    {
                        this.$spell = $spell;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var25_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                dto = WrSpellDto.Companion.fromModel(this.$spell);
                                var3_4 = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_spells");
                                value\1 = dto;
                                $i$f$upsert\1\235 = 0;
                                var7_10 = $this\1;
                                values\2 = CollectionsKt.listOf((Object)value\1);
                                $i$f$upsert\2\407 = 0;
                                var11_17 = var10_16 = new UpsertRequestBuilder(((Postgrest.Config)this_\2.getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-upsert-PostgrestQueryBuilder$upsert$5\10\408\0 = false;
                                <this>\10 = var11_17;
                                requestBuilder\2 = var10_16;
                                $this$encodeToJsonElement\3 = this_\2.getPostgrest().getSerializer();
                                $i$f$encodeToJsonElement\3\410 = false;
                                var16_23 = (Json)Json.Default;
                                $this$encode\4 /* !! */  = $this$encodeToJsonElement\3;
                                $i$f$encode\4\411 = false;
                                $this$encode\4 /* !! */  = $this$encode\4 /* !! */ .encode(Reflection.typeOf(List.class, (KTypeProjection)KTypeProjection.Companion.invariant(Reflection.typeOf(WrSpellDto.class))), (Object)values\2);
                                $i$f$decodeFromString\5\411 = false;
                                this_\5.getSerializersModule();
                                body\2 = JsonElementKt.getJsonArray((JsonElement)((JsonElement)this_\5.decodeFromString((DeserializationStrategy)JsonElement.Companion.serializer(), (String)string\5)));
                                $this$map\6 = (Iterable)body\2;
                                $i$f$map\6\414 = false;
                                string\5 = $this$map\6;
                                destination\7 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\6, (int)10));
                                $i$f$mapTo\7\415 = false;
                                for (T item\7 : $this$mapTo\7) {
                                    var22_35 = (JsonElement)item\7;
                                    var23_36 = destination\7;
                                    $i$a$-map-PostgrestQueryBuilder$upsert$columns$1\8\417\2 = false;
                                    var23_36.add(JsonElementKt.getJsonObject((JsonElement)it\8).keySet());
                                }
                                columns\2 = CollectionsKt.distinct((Iterable)CollectionsKt.flatten((Iterable)((List)destination\7)));
                                if (((Collection)columns\2).isEmpty() == false) {
                                    requestBuilder\2.getParams().put("columns", CollectionsKt.listOf((Object)CollectionsKt.joinToString$default((Iterable)columns\2, (CharSequence)",", null, null, (int)0, null, null, (int)62, null)));
                                }
                                v0 = requestBuilder\2.getOnConflict();
                                if (v0 != null) {
                                    it\2 = v0;
                                    $i$a$-let-PostgrestQueryBuilder$upsert$3\9\420\2 = false;
                                    requestBuilder\2.getParams().put("on_conflict", CollectionsKt.listOf((Object)it\2));
                                }
                                var16_25 = requestBuilder\2.getReturning();
                                var17_26 = requestBuilder\2.getCount();
                                var18_30 = UtilsKt.mapToFirstValue((Map)requestBuilder\2.getParams());
                                var19_31 = requestBuilder\2.getDefaultToNull();
                                var20_33 = requestBuilder\2.getIgnoreDuplicates();
                                var21_34 = this_\2.getSchema();
                                var22_35 = requestBuilder\2.getHeaders().build();
                                insertRequest\2 = new InsertRequest(true, var16_25, var17_26, var20_33 != false, var19_31 != false, body\2, var18_30, var21_34, (Headers)var22_35);
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)dto);
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)$this\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)value\1);
                                this.L$3 = SpillingKt.nullOutSpilledVariable((Object)this_\2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable((Object)values\2);
                                this.L$5 = SpillingKt.nullOutSpilledVariable((Object)body\2);
                                this.L$6 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\2);
                                this.L$7 = SpillingKt.nullOutSpilledVariable((Object)columns\2);
                                this.L$8 = SpillingKt.nullOutSpilledVariable((Object)insertRequest\2);
                                this.I$0 = $i$f$upsert\1\235;
                                this.I$1 = $i$f$upsert\2\407;
                                this.label = 1;
                                v1 = RestRequestExecutor.INSTANCE.execute(this_\2.getPostgrest(), this_\2.getTable(), (PostgrestRequest)insertRequest\2, (Continuation)this);
                                ** if (v1 != var25_2) goto lbl77
lbl76:
                                // 1 sources

                                return var25_2;
lbl77:
                                // 1 sources

                                ** GOTO lbl96
                            }
                            case 1: {
                                $i$f$upsert\2\407 = this.I$1;
                                $i$f$upsert\1\235 = this.I$0;
                                insertRequest\2 = (InsertRequest)this.L$8;
                                columns\2 = (List)this.L$7;
                                requestBuilder\2 = (UpsertRequestBuilder)this.L$6;
                                body\2 = (JsonArray)this.L$5;
                                values\2 = (List)this.L$4;
                                this_\2 = (PostgrestQueryBuilder)this.L$3;
                                value\1 = (WrSpellDto)this.L$2;
                                $this\1 = (PostgrestQueryBuilder)this.L$1;
                                dto = (WrSpellDto)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v1 = $result;
lbl96:
                                    // 2 sources

                                    var5_38 = current = CollectionsKt.toMutableList((Collection)WildRiftRepository.INSTANCE.getSummonerSpells());
                                    var6_9 = this.$spell;
                                    $i$f$indexOfFirst\11\237 = false;
                                    index\11 = 0;
                                    for (E item\11 : $this$indexOfFirst\11) {
                                        it\12 = (SummonerSpellItem)item\11;
                                        $i$a$-indexOfFirst-WildRiftSupabaseRepository$saveSpell$2$index$1\12\437\0 = false;
                                        if (!Intrinsics.areEqual((Object)it\12.getId(), (Object)var6_9.getId())) ** GOTO lbl106
                                        v2 = index\11;
                                        ** GOTO lbl109
lbl106:
                                        // 1 sources

                                        ++index\11;
                                    }
                                    v2 = index = -1;
lbl109:
                                    // 2 sources

                                    if (index != -1) {
                                        current.set(index, this.$spell);
                                    } else {
                                        Boxing.boxBoolean((boolean)current.add(this.$spell));
                                    }
                                    WildRiftRepository.INSTANCE.setSummonerSpells(current);
                                    var2_3 = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    var2_3 = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3);
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                spell = (SummonerSpellItem)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object deleteSpell-gIAlu-s(@NotNull String spellId, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof deleteSpell.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.deleteSpell-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)spellId);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>(spellId, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int I$0;
                    int label;
                    final /* synthetic */ String $spellId;
                    {
                        this.$spellId = $spellId;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     * Could not resolve type clashes
                     */
                    public final Object invokeSuspend(Object $result) {
                        var15_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                var2_3 /* !! */  = WildRiftSupabaseRepository.access$getPostgrest(WildRiftSupabaseRepository.INSTANCE).from("wr_spells");
                                var3_4 = this.$spellId;
                                $i$f$delete\1\248 = 0;
                                $this$invokeSuspend_u24lambda_u241\2 = var5_7 = new PostgrestRequestBuilder(((Postgrest.Config)this_\1 /* !! */ .getPostgrest().getConfig()).getPropertyConversionMethod());
                                $i$a$-delete-WildRiftSupabaseRepository$deleteSpell$2$1\2\404\0 = false;
                                this_\3 = $this$invokeSuspend_u24lambda_u241\2;
                                $i$f$filter\3\248 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4 = filter\3 = new PostgrestFilterBuilder(this_\3.getPropertyConversionMethod(), this_\3.getParams(), false, 4, null);
                                $i$a$-filter-WildRiftSupabaseRepository$deleteSpell$2$1$1\4\406\2 = false;
                                $this$invokeSuspend_u24lambda_u241_u24lambda_u240\4.eq("id", (Object)var3_4);
                                requestBuilder\1 = var5_7;
                                deleteRequest\1 = new DeleteRequest(requestBuilder\1.getReturning(), requestBuilder\1.getCount(), UtilsKt.mapToFirstValue((Map)requestBuilder\1.getParams()), this_\1 /* !! */ .getSchema(), requestBuilder\1.getHeaders().build());
                                this.L$0 = SpillingKt.nullOutSpilledVariable((Object)this_\1 /* !! */ );
                                this.L$1 = SpillingKt.nullOutSpilledVariable((Object)deleteRequest\1);
                                this.L$2 = SpillingKt.nullOutSpilledVariable((Object)requestBuilder\1);
                                this.I$0 = $i$f$delete\1\248;
                                this.label = 1;
                                v0 = RestRequestExecutor.INSTANCE.execute(this_\1 /* !! */ .getPostgrest(), this_\1 /* !! */ .getTable(), (PostgrestRequest)deleteRequest\1, (Continuation)this);
                                ** if (v0 != var15_2) goto lbl29
lbl28:
                                // 1 sources

                                return var15_2;
lbl29:
                                // 1 sources

                                ** GOTO lbl40
                            }
                            case 1: {
                                $i$f$delete\1\248 = this.I$0;
                                requestBuilder\1 = (PostgrestRequestBuilder)this.L$2;
                                deleteRequest\1 = (DeleteRequest)this.L$1;
                                this_\1 /* !! */  = (PostgrestQueryBuilder)this.L$0;
                                try {
                                    ResultKt.throwOnFailure((Object)$result);
                                    v0 = $result;
lbl40:
                                    // 2 sources

                                    this_\1 /* !! */  = WildRiftRepository.INSTANCE.getSummonerSpells();
                                    var3_4 = this.$spellId;
                                    var14_18 = WildRiftRepository.INSTANCE;
                                    $i$f$filterNot\5\249 = false;
                                    deleteRequest\1 = $this$filterNot\5;
                                    destination\6 = new ArrayList<E>();
                                    $i$f$filterNotTo\6\416 = false;
                                    for (T element\6 : $this$filterNotTo\6) {
                                        it\7 = (SummonerSpellItem)element\6;
                                        $i$a$-filterNot-WildRiftSupabaseRepository$deleteSpell$2$2\7\417\0 = false;
                                        if (Intrinsics.areEqual((Object)it\7.getId(), (Object)var3_4)) continue;
                                        destination\6.add(element\6);
                                    }
                                    var14_18.setSummonerSpells((List)destination\6);
                                    var2_3 /* !! */  = Result.constructor-impl((Object)Unit.INSTANCE);
                                }
                                catch (Exception e) {
                                    var2_3 /* !! */  = Result.constructor-impl((Object)ResultKt.createFailure((Throwable)e));
                                }
                                return Result.box-impl((Object)var2_3 /* !! */ );
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                spellId = (String)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object syncAllFromSupabase-gIAlu-s(@NotNull Context context, @NotNull Continuation<? super Result<String>> $completion) {
        if (!($completion instanceof syncAllFromSupabase.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.syncAllFromSupabase-gIAlu-s(null, (Continuation<? super Result<String>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends String>>, Object>(context, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    int label;
                    final /* synthetic */ Context $context;
                    {
                        this.$context = $context;
                        super(2, $completion);
                    }

                    /*
                     * Exception decompiling
                     */
                    public final Object invokeSuspend(Object $result) {
                        /*
                         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                         * 
                         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
                         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
                         *     at org.benf.cfr.reader.entities.Method.dump(Method.java:598)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.ClassFileDumperAnonymousInner.dumpWithArgs(ClassFileDumperAnonymousInner.java:87)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.ConstructorInvokationAnonymousInner.dumpInner(ConstructorInvokationAnonymousInner.java:82)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression.dumpInner(CastExpression.java:114)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:139)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression.dumpInner(CastExpression.java:114)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dump(AbstractExpression.java:98)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation.dumpInner(StaticFunctionInvokation.java:143)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dump(AbstractExpression.java:98)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment.dump(StructuredAssignment.java:69)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredCase.dump(StructuredCase.java:94)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredSwitch.dump(StructuredSwitch.java:59)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.dump(AttributeCode.java:135)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.entities.Method.dump(Method.java:627)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.AbstractClassFileDumper.dumpMethods(AbstractClassFileDumper.java:211)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.ClassFileDumperNormal.dump(ClassFileDumperNormal.java:70)
                         *     at org.benf.cfr.reader.entities.ClassFile.dump(ClassFile.java:1167)
                         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:952)
                         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
                         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
                         *     at org.benf.cfr.reader.Main.main(Main.java:54)
                         */
                        throw new IllegalStateException("Decompilation failed");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<String>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                context = (Context)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object seedAllDataToSupabase-gIAlu-s(@NotNull Function3<? super Integer, ? super Integer, ? super String, Unit> onProgress, @NotNull Continuation<? super Result<Unit>> $completion) {
        if (!($completion instanceof seedAllDataToSupabase.1)) ** GOTO lbl-1000
        var4_3 = $completion;
        if ((var4_3.label & -2147483648) != 0) {
            var4_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ WildRiftSupabaseRepository this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    Object object = this.this$0.seedAllDataToSupabase-gIAlu-s(null, (Continuation<? super Result<Unit>>)((Continuation)this));
                    if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object;
                    }
                    return Result.box-impl((Object)object);
                }
            };
        }
        $result = $continuation.result;
        var5_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)onProgress);
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object>((Function3<? super Integer, ? super Integer, ? super String, Unit>)onProgress, null){
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    Object L$8;
                    Object L$9;
                    Object L$10;
                    Object L$11;
                    Object L$12;
                    Object L$13;
                    Object L$14;
                    Object L$15;
                    Object L$16;
                    Object L$17;
                    Object L$18;
                    int I$0;
                    int I$1;
                    int I$2;
                    int I$3;
                    int label;
                    final /* synthetic */ Function3<Integer, Integer, String, Unit> $onProgress;
                    {
                        this.$onProgress = $onProgress;
                        super(2, $completion);
                    }

                    /*
                     * Exception decompiling
                     */
                    public final Object invokeSuspend(Object $result) {
                        /*
                         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                         * 
                         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 16[WHILELOOP]
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
                         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
                         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
                         *     at org.benf.cfr.reader.entities.Method.dump(Method.java:598)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.ClassFileDumperAnonymousInner.dumpWithArgs(ClassFileDumperAnonymousInner.java:87)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.ConstructorInvokationAnonymousInner.dumpInner(ConstructorInvokationAnonymousInner.java:82)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression.dumpInner(CastExpression.java:114)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:139)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression.dumpInner(CastExpression.java:114)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dump(AbstractExpression.java:98)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation.dumpInner(StaticFunctionInvokation.java:143)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dumpWithOuterPrecedence(AbstractExpression.java:142)
                         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractExpression.dump(AbstractExpression.java:98)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment.dump(StructuredAssignment.java:69)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredCase.dump(StructuredCase.java:94)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredSwitch.dump(StructuredSwitch.java:59)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.dump(Block.java:564)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.dump(Op04StructuredStatement.java:220)
                         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.dump(AttributeCode.java:135)
                         *     at org.benf.cfr.reader.state.TypeUsageCollectingDumper.dump(TypeUsageCollectingDumper.java:194)
                         *     at org.benf.cfr.reader.entities.Method.dump(Method.java:627)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.AbstractClassFileDumper.dumpMethods(AbstractClassFileDumper.java:211)
                         *     at org.benf.cfr.reader.entities.classfilehelpers.ClassFileDumperNormal.dump(ClassFileDumperNormal.java:70)
                         *     at org.benf.cfr.reader.entities.ClassFile.dump(ClassFile.java:1167)
                         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:952)
                         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
                         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
                         *     at org.benf.cfr.reader.Main.main(Main.java:54)
                         */
                        throw new IllegalStateException("Decompilation failed");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Result<Unit>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                if (v0 == var5_5) {
                    return var5_5;
                }
                ** GOTO lbl22
            }
            case 1: {
                onProgress = (Function3)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl22:
                // 2 sources

                return ((Result)v0).unbox-impl();
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public static final /* synthetic */ Postgrest access$getPostgrest(WildRiftSupabaseRepository $this) {
        return $this.getPostgrest();
    }
}
