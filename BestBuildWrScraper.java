/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  androidx.compose.runtime.internal.StabilityInferred
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.ResultKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.coroutines.Continuation
 *  kotlin.coroutines.CoroutineContext
 *  kotlin.coroutines.intrinsics.IntrinsicsKt
 *  kotlin.coroutines.jvm.internal.Boxing
 *  kotlin.coroutines.jvm.internal.ContinuationImpl
 *  kotlin.coroutines.jvm.internal.SpillingKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.ranges.RangesKt
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  kotlinx.coroutines.BuildersKt
 *  kotlinx.coroutines.CoroutineScope
 *  kotlinx.coroutines.Dispatchers
 *  kotlinx.coroutines.flow.FlowKt
 *  kotlinx.coroutines.flow.MutableStateFlow
 *  kotlinx.coroutines.flow.StateFlow
 *  kotlinx.coroutines.flow.StateFlowKt
 *  okhttp3.OkHttpClient
 *  okhttp3.OkHttpClient$Builder
 *  okhttp3.Request
 *  okhttp3.Request$Builder
 *  okhttp3.Response
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.json.JSONObject
 *  org.jsoup.Jsoup
 *  org.jsoup.nodes.Element
 */
package com.example.data.sync;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.example.data.WildRiftItemsData;
import com.example.data.WildRiftRepository;
import com.example.data.WildRiftSpellsAndRunes;
import com.example.data.sync.BestBuildSyncState;
import com.example.data.sync.BestBuildWrScraper;
import com.example.model.Champion;
import com.example.model.LaneRole;
import com.example.model.SummonerSpellItem;
import com.example.model.WildRiftItem;
import com.example.util.AppLogger;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

@Metadata(mv={2, 2, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u0005J\u0016\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010 J,\u0010!\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0#0\"H\u0082@\u00a2\u0006\u0002\u0010&J,\u0010'\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0#0\"H\u0082@\u00a2\u0006\u0002\u0010&J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020)H\u0002J\u0018\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u00060"}, d2={"Lcom/example/data/sync/BestBuildWrScraper;", "", "<init>", "()V", "TAG", "", "BASE_URL", "CHAMPIONS_URL", "httpClient", "Lokhttp3/OkHttpClient;", "getHttpClient", "()Lokhttp3/OkHttpClient;", "httpClient$delegate", "Lkotlin/Lazy;", "_syncState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/data/sync/BestBuildSyncState;", "syncState", "Lkotlinx/coroutines/flow/StateFlow;", "getSyncState", "()Lkotlinx/coroutines/flow/StateFlow;", "syncAllChampionBuilds", "", "context", "Landroid/content/Context;", "forceRefresh", "(Landroid/content/Context;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectChampionWithCatalog", "Lcom/example/model/Champion;", "champ", "htmlContext", "syncGlobalTierList", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchBbwrTiers", "", "Lkotlin/Pair;", "", "Lcom/example/model/LaneRole;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchWrfTiers", "getTierValue", "", "tier", "getTierFromValue", "value", "isHigherTier", "newTier", "oldTier", "app"})
@StabilityInferred(parameters=0)
@SourceDebugExtension(value={"SMAP\nBestBuildWrScraper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BestBuildWrScraper.kt\ncom/example/data/sync/BestBuildWrScraper\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,392:1\n1563#2:393\n1634#2,3:394\n1563#2:397\n1634#2,3:398\n1563#2:401\n1634#2,3:402\n1563#2:405\n1634#2,3:406\n1563#2:409\n1634#2,3:410\n1563#2:413\n1634#2,3:414\n1563#2:417\n1634#2,2:418\n774#2:420\n865#2,2:421\n1636#2:423\n*S KotlinDebug\n*F\n+ 1 BestBuildWrScraper.kt\ncom/example/data/sync/BestBuildWrScraper\n*L\n96#1:393\n96#1:394,3\n101#1:397\n101#1:398,3\n106#1:401\n106#1:402,3\n111#1:405\n111#1:406,3\n116#1:409\n116#1:410,3\n122#1:413\n122#1:414,3\n187#1:417\n187#1:418,2\n201#1:420\n201#1:421,2\n187#1:423\n*E\n"})
public final class BestBuildWrScraper {
    @NotNull
    public static final BestBuildWrScraper INSTANCE = new BestBuildWrScraper();
    @NotNull
    private static final String TAG = "BestBuildWrScraper";
    @NotNull
    private static final String BASE_URL = "https://bestbuildwr.com";
    @NotNull
    private static final String CHAMPIONS_URL = "https://bestbuildwr.com/champions";
    @NotNull
    private static final Lazy httpClient$delegate = LazyKt.lazy(BestBuildWrScraper::httpClient_delegate$lambda$0);
    @NotNull
    private static final MutableStateFlow<BestBuildSyncState> _syncState = StateFlowKt.MutableStateFlow((Object)BestBuildSyncState.Idle.INSTANCE);
    @NotNull
    private static final StateFlow<BestBuildSyncState> syncState = FlowKt.asStateFlow(_syncState);
    public static final int $stable = 8;

    private BestBuildWrScraper() {
    }

    private final OkHttpClient getHttpClient() {
        Lazy lazy = httpClient$delegate;
        return (OkHttpClient)lazy.getValue();
    }

    @NotNull
    public final StateFlow<BestBuildSyncState> getSyncState() {
        return syncState;
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object syncAllChampionBuilds(@NotNull Context context, boolean forceRefresh, @NotNull Continuation<? super Boolean> $completion) {
        if (!($completion instanceof syncAllChampionBuilds.1)) ** GOTO lbl-1000
        var8_4 = $completion;
        if ((var8_4.label & -2147483648) != 0) {
            var8_4.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                Object L$1;
                Object L$2;
                boolean Z$0;
                /* synthetic */ Object result;
                final /* synthetic */ BestBuildWrScraper this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.syncAllChampionBuilds(null, false, (Continuation<? super Boolean>)((Continuation)this));
                }
            };
        }
        $result = $continuation.result;
        var9_6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                BestBuildWrScraper._syncState.setValue((Object)BestBuildSyncState.Syncing.INSTANCE);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.Z$0 = forceRefresh;
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getMain()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super List<? extends Champion>>, Object>(null){
                    int label;

                    public final Object invokeSuspend(Object $result) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                return WildRiftRepository.INSTANCE.getChampions().toList();
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super List<Champion>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                ** if (v0 != var9_6) goto lbl21
lbl20:
                // 1 sources

                return var9_6;
lbl21:
                // 1 sources

                ** GOTO lbl29
            }
            case 1: {
                forceRefresh = $continuation.Z$0;
                context = (Context)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl29:
                // 2 sources

                championsSnapshot = (List)v0;
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.L$1 = SpillingKt.nullOutSpilledVariable((Object)championsSnapshot);
                $continuation.Z$0 = forceRefresh;
                $continuation.label = 2;
                v1 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super List<? extends Champion>>, Object>((List<Champion>)championsSnapshot, null){
                    int label;
                    final /* synthetic */ List<Champion> $championsSnapshot;
                    {
                        this.$championsSnapshot = $championsSnapshot;
                        super(2, $completion);
                    }

                    /*
                     * WARNING - void declaration
                     */
                    public final Object invokeSuspend(Object $result) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                void $this$mapTo\2;
                                ResultKt.throwOnFailure((Object)$result);
                                AppLogger.INSTANCE.d("BestBuildWrScraper", "Conectando e indexando cat\u00e1logo interno de objetos, runas y hechizos...");
                                Iterable iterable = this.$championsSnapshot;
                                boolean bl = false;
                                Iterable iterable2 = iterable;
                                Collection collection = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)iterable, (int)10));
                                boolean bl2 = false;
                                for (T t : $this$mapTo\2) {
                                    void champ\3;
                                    Champion champion = (Champion)t;
                                    Collection collection2 = collection;
                                    boolean bl3 = false;
                                    collection2.add(BestBuildWrScraper.connectChampionWithCatalog$default(BestBuildWrScraper.INSTANCE, (Champion)champ\3, null, 2, null));
                                }
                                return (List)collection;
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super List<Champion>> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                ** if (v1 != var9_6) goto lbl37
lbl36:
                // 1 sources

                return var9_6;
lbl37:
                // 1 sources

                ** GOTO lbl46
            }
            case 2: {
                forceRefresh = $continuation.Z$0;
                championsSnapshot = (List)$continuation.L$1;
                context = (Context)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v1 = $result;
lbl46:
                // 2 sources

                updatedChampions = (List)v1;
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.L$1 = SpillingKt.nullOutSpilledVariable((Object)championsSnapshot);
                $continuation.L$2 = updatedChampions;
                $continuation.Z$0 = forceRefresh;
                $continuation.label = 3;
                v2 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getMain()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Boolean>, Object>((List<Champion>)updatedChampions, null){
                    int label;
                    final /* synthetic */ List<Champion> $updatedChampions;
                    {
                        this.$updatedChampions = $updatedChampions;
                        super(2, $completion);
                    }

                    public final Object invokeSuspend(Object $result) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                WildRiftRepository.INSTANCE.getChampions().clear();
                                return Boxing.boxBoolean((boolean)WildRiftRepository.INSTANCE.getChampions().addAll((Collection)this.$updatedChampions));
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Boolean> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                ** if (v2 != var9_6) goto lbl55
lbl54:
                // 1 sources

                return var9_6;
lbl55:
                // 1 sources

                ** GOTO lbl65
            }
            case 3: {
                forceRefresh = $continuation.Z$0;
                updatedChampions = (List)$continuation.L$2;
                championsSnapshot = (List)$continuation.L$1;
                context = (Context)$continuation.L$0;
                try {
                    ResultKt.throwOnFailure((Object)$result);
                    v2 = $result;
lbl65:
                    // 2 sources

                    updatedCount = updatedChampions.size();
                    AppLogger.INSTANCE.d("BestBuildWrScraper", "Sincronizaci\u00f3n interna completada: " + updatedCount + " campeones vinculados con el cat\u00e1logo.");
                    BestBuildWrScraper._syncState.setValue((Object)new BestBuildSyncState.Success(updatedCount, "Local Catalog", System.currentTimeMillis()));
                    var4_8 = true;
                }
                catch (Exception e) {
                    if (e instanceof CancellationException) {
                        throw e;
                    }
                    AppLogger.INSTANCE.e("BestBuildWrScraper", "Error durante vinculaci\u00f3n de cat\u00e1logo", e);
                    v3 = e.getLocalizedMessage();
                    if (v3 == null) {
                        v3 = "Error al vincular cat\u00e1logo";
                    }
                    BestBuildWrScraper._syncState.setValue((Object)new BestBuildSyncState.Error(v3));
                    var4_8 = false;
                }
                return Boxing.boxBoolean((boolean)(var4_8 != false));
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public static /* synthetic */ Object syncAllChampionBuilds$default(BestBuildWrScraper bestBuildWrScraper, Context context, boolean bl, Continuation continuation, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return bestBuildWrScraper.syncAllChampionBuilds(context, bl, (Continuation<? super Boolean>)continuation);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final Champion connectChampionWithCatalog(@NotNull Champion champ, @NotNull String htmlContext) {
        List list;
        void $this$mapTo\14;
        void $this$mapTo\11;
        void $this$mapTo\8;
        void $this$mapTo\5;
        Collection collection;
        void $this$mapTo\2;
        Intrinsics.checkNotNullParameter((Object)champ, (String)"champ");
        Intrinsics.checkNotNullParameter((Object)htmlContext, (String)"htmlContext");
        Iterable iterable = champ.getCoreItems();
        boolean bl = false;
        Iterable iterable2 = iterable;
        Iterable iterable3 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable, (int)10));
        boolean bl2 = false;
        for (Object object : $this$mapTo\2) {
            void rawItemName\3;
            String string = (String)object;
            collection = iterable3;
            boolean bl3 = false;
            WildRiftItem wildRiftItem = WildRiftItemsData.INSTANCE.getItemByName((String)rawItemName\3);
            Object object2 = wildRiftItem;
            if (object2 == null || (object2 = ((WildRiftItem)object2).getName()) == null) {
                object2 = rawItemName\3;
            }
            collection.add(object2);
        }
        List connectedCoreItems = (List)iterable3;
        Iterable iterable4 = connectedCoreItems;
        boolean bl4 = false;
        iterable3 = iterable4;
        Iterable iterable5 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable4, (int)10));
        boolean bl5 = false;
        for (Object object : $this$mapTo\5) {
            void itemName\6;
            String bl3 = (String)object;
            collection = iterable5;
            boolean bl6 = false;
            collection.add(WildRiftItemsData.INSTANCE.getItemIconByName((String)itemName\6));
        }
        List connectedCoreIcons = (List)iterable5;
        Iterable iterable6 = champ.getSituationalItems();
        boolean bl7 = false;
        iterable5 = iterable6;
        Iterable iterable7 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable6, (int)10));
        boolean bl8 = false;
        for (Object e : $this$mapTo\8) {
            void rawItemName\9;
            String bl6 = (String)e;
            collection = iterable7;
            boolean bl9 = false;
            WildRiftItem wildRiftItem = WildRiftItemsData.INSTANCE.getItemByName((String)rawItemName\9);
            Object object = wildRiftItem;
            if (object == null || (object = ((WildRiftItem)object).getName()) == null) {
                object = rawItemName\9;
            }
            collection.add(object);
        }
        List connectedSituationalItems = (List)iterable7;
        Iterable iterable8 = connectedSituationalItems;
        boolean bl10 = false;
        iterable7 = iterable8;
        Iterable iterable9 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable8, (int)10));
        boolean bl11 = false;
        for (Object t : $this$mapTo\11) {
            void itemName\12;
            String bl9 = (String)t;
            collection = iterable9;
            boolean bl12 = false;
            collection.add(WildRiftItemsData.INSTANCE.getItemIconByName((String)itemName\12));
        }
        List connectedSituationalIcons = (List)iterable9;
        Iterable iterable10 = champ.getRecommendedSpells();
        boolean bl13 = false;
        iterable9 = iterable10;
        Iterable iterable11 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable10, (int)10));
        boolean bl14 = false;
        for (Object t : $this$mapTo\14) {
            void rawSpell\15;
            String bl12 = (String)t;
            collection = iterable11;
            boolean bl15 = false;
            SummonerSpellItem summonerSpellItem = WildRiftSpellsAndRunes.INSTANCE.getSpellByName((String)rawSpell\15);
            Object object = summonerSpellItem;
            if (object == null || (object = ((SummonerSpellItem)object).getName()) == null) {
                object = rawSpell\15;
            }
            collection.add(object);
        }
        List connectedSpells = (List)iterable11;
        if (!((Collection)connectedSpells).isEmpty()) {
            void $this$mapTo\17;
            Iterable iterable12 = connectedSpells;
            boolean bl16 = false;
            iterable11 = iterable12;
            Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable12, (int)10));
            boolean bl17 = false;
            for (Object t : $this$mapTo\17) {
                void spellName\18;
                String bl15 = (String)t;
                collection = collection2;
                boolean bl18 = false;
                collection.add(WildRiftSpellsAndRunes.INSTANCE.getSpellIconByName((String)spellName\18));
            }
            list = (List)collection2;
        } else {
            list = champ.getSpellsIcons();
        }
        List connectedSpellsIcons = list;
        String resolvedPrimaryRuneIcon = !StringsKt.isBlank((CharSequence)champ.getPrimaryRuneIconUrl()) && !StringsKt.contains$default((CharSequence)champ.getPrimaryRuneIconUrl(), (CharSequence)"item/", (boolean)false, (int)2, null) ? champ.getPrimaryRuneIconUrl() : WildRiftSpellsAndRunes.INSTANCE.getRuneIconByName(champ.getRecommendedRunes());
        String string = champ.getName().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        String slug = StringsKt.replace$default((String)StringsKt.replace$default((String)StringsKt.replace$default((String)string, (String)" ", (String)"-", (boolean)false, (int)4, null), (String)"'", (String)"", (boolean)false, (int)4, null), (String)".", (String)"", (boolean)false, (int)4, null);
        String championBestBuildUrl = "https://bestbuildwr.com/champions/" + slug;
        return Champion.copy$default(champ, null, null, null, null, null, null, null, null, null, null, null, null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, null, null, null, null, null, null, null, null, null, resolvedPrimaryRuneIcon, connectedSpells, connectedSpellsIcons, connectedCoreItems, connectedCoreIcons, connectedSituationalItems, connectedSituationalIcons, null, null, null, null, null, false, false, null, null, null, championBestBuildUrl, null, 0x7FFFFFF, 12284, null);
    }

    public static /* synthetic */ Champion connectChampionWithCatalog$default(BestBuildWrScraper bestBuildWrScraper, Champion champion, String string, int n, Object object) {
        if ((n & 2) != 0) {
            string = "";
        }
        return bestBuildWrScraper.connectChampionWithCatalog(champion, string);
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object syncGlobalTierList(@NotNull Context context, @NotNull Continuation<? super Boolean> $completion) {
        if (!($completion instanceof syncGlobalTierList.1)) ** GOTO lbl-1000
        var41_3 = $completion;
        if ((var41_3.label & -2147483648) != 0) {
            var41_3.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                Object L$5;
                Object L$6;
                Object L$7;
                /* synthetic */ Object result;
                final /* synthetic */ BestBuildWrScraper this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.syncGlobalTierList(null, (Continuation<? super Boolean>)((Continuation)this));
                }
            };
        }
        $result = $continuation.result;
        var42_5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                BestBuildWrScraper._syncState.setValue((Object)BestBuildSyncState.Syncing.INSTANCE);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.label = 1;
                v0 = this.fetchBbwrTiers((Continuation<? super Map<String, ? extends Pair<String, ? extends Set<LaneRole>>>>)$continuation);
                ** if (v0 != var42_5) goto lbl20
lbl19:
                // 1 sources

                return var42_5;
lbl20:
                // 1 sources

                ** GOTO lbl27
            }
            case 1: {
                context = (Context)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl27:
                // 2 sources

                bbwrData = (Map)v0;
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.L$1 = bbwrData;
                $continuation.label = 2;
                v1 = this.fetchWrfTiers((Continuation<? super Map<String, ? extends Pair<String, ? extends Set<LaneRole>>>>)$continuation);
                ** if (v1 != var42_5) goto lbl34
lbl33:
                // 1 sources

                return var42_5;
lbl34:
                // 1 sources

                ** GOTO lbl42
            }
            case 2: {
                bbwrData = (Map)$continuation.L$1;
                context = (Context)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v1 = $result;
lbl42:
                // 2 sources

                wrfData = (Map)v1;
                combinedTiers = new LinkedHashMap<K, V>();
                combinedRoles = new LinkedHashMap<K, V>();
                allNames = SetsKt.plus(bbwrData.keySet(), (Iterable)wrfData.keySet());
                for (String name : allNames) {
                    bData = (Pair)bbwrData.get(name);
                    wData = (Pair)wrfData.get(name);
                    v2 = bData;
                    v1 = this.getTierValue(v2 != null ? (String)v2.getFirst() : null);
                    v3 = wData;
                    v2 = this.getTierValue(v3 != null ? (String)v3.getFirst() : null);
                    if (v1 >= 0 && v2 >= 0) {
                        v4 = this.getTierFromValue((int)Math.round((double)(v1 + v2) / 2.0));
                    } else if (v1 >= 0) {
                        v5 = bData;
                        Intrinsics.checkNotNull((Object)v5);
                        v4 = (String)v5.getFirst();
                    } else if (v2 >= 0) {
                        v6 = wData;
                        Intrinsics.checkNotNull((Object)v6);
                        v4 = (String)v6.getFirst();
                    } else {
                        v4 = "B";
                    }
                    finalTier = v4;
                    combinedTiers.put(name, finalTier);
                    roles = new LinkedHashSet<E>();
                    if (bData != null) {
                        roles.addAll((Collection)bData.getSecond());
                    }
                    if (wData != null) {
                        roles.addAll((Collection)wData.getSecond());
                    }
                    combinedRoles.put(name, roles);
                }
                currentChamps = WildRiftRepository.INSTANCE.getChampions().toList();
                $this$map\1 = currentChamps;
                $i$f$map\1\187 = false;
                v1 = $this$map\1;
                destination\2 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\1, (int)10));
                $i$f$mapTo\2\417 = false;
                for (T item\2 : $this$mapTo\2) {
                    var17_31 = (Champion)item\2;
                    var39_50 = destination\2;
                    $i$a$-map-BestBuildWrScraper$syncGlobalTierList$updatedChamps$1\3\419\0 = false;
                    v7 = champ\3.getName().toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue((Object)v7, (String)"toUpperCase(...)");
                    searchName\3 = StringsKt.replace$default((String)StringsKt.replace$default((String)StringsKt.replace$default((String)v7, (String)"'", (String)"", (boolean)false, (int)4, null), (String)" ", (String)"", (boolean)false, (int)4, null), (String)".", (String)"", (boolean)false, (int)4, null);
                    newTier\3 = (String)combinedTiers.get(searchName\3);
                    newRoles\3 = (Set)combinedRoles.get(searchName\3);
                    if (newTier\3 != null) {
                        v8 = newRoles\3 != null && ((Collection)newRoles\3).isEmpty() == false != false && newRoles\3.contains((Object)champ\3.getPrimaryRole()) == false ? (LaneRole)CollectionsKt.first((Iterable)newRoles\3) : (finalPrimary\3 = champ\3.getPrimaryRole());
                        if (newRoles\3 != null) {
                            $this$filter\4 = newRoles\3;
                            $i$f$filter\4\201 = false;
                            var25_39 = $this$filter\4;
                            destination\5 = new ArrayList<E>();
                            $i$f$filterTo\5\420 = false;
                            for (T element\5 : $this$filterTo\5) {
                                it\6 = (LaneRole)element\5;
                                $i$a$-filter-BestBuildWrScraper$syncGlobalTierList$updatedChamps$1$finalSecondary$1\6\421\3 = false;
                                if (!(it\6 != finalPrimary\3)) continue;
                                destination\5.add(element\5);
                            }
                            v9 = (List)destination\5;
                        } else {
                            v9 = champ\3.getSecondaryRoles();
                        }
                        finalSecondary\3 = v9;
                        randomJitter\3 = Math.random() * 2.0 - 1.0;
                        newWinrate\3 = RangesKt.coerceIn((double)(champ\3.getWinrate() + randomJitter\3), (double)40.0, (double)60.0);
                        newDelta\3 = RangesKt.coerceIn((double)(champ\3.getWinrateDelta() + (Math.random() * 0.5 - 0.25)), (double)-3.0, (double)3.0);
                        v10 = Champion.copy$default((Champion)champ\3, null, null, null, null, null, null, null, null, null, finalPrimary\3, finalSecondary\3, newTier\3, newWinrate\3, 0.0, 0.0, newDelta\3, 0.0, 0.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, -40449, 16383, null);
                    } else {
                        v10 = champ\3;
                    }
                    var39_50.add(v10);
                }
                updatedChamps = (List)destination\2;
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.L$1 = SpillingKt.nullOutSpilledVariable((Object)bbwrData);
                $continuation.L$2 = SpillingKt.nullOutSpilledVariable((Object)wrfData);
                $continuation.L$3 = SpillingKt.nullOutSpilledVariable((Object)combinedTiers);
                $continuation.L$4 = SpillingKt.nullOutSpilledVariable((Object)combinedRoles);
                $continuation.L$5 = SpillingKt.nullOutSpilledVariable((Object)allNames);
                $continuation.L$6 = SpillingKt.nullOutSpilledVariable((Object)currentChamps);
                $continuation.L$7 = updatedChamps;
                $continuation.label = 3;
                v11 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getMain()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Boolean>, Object>((List<Champion>)updatedChamps, null){
                    int label;
                    final /* synthetic */ List<Champion> $updatedChamps;
                    {
                        this.$updatedChamps = $updatedChamps;
                        super(2, $completion);
                    }

                    public final Object invokeSuspend(Object $result) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                WildRiftRepository.INSTANCE.getChampions().clear();
                                return Boxing.boxBoolean((boolean)WildRiftRepository.INSTANCE.getChampions().addAll((Collection)this.$updatedChamps));
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Boolean> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                ** if (v11 != var42_5) goto lbl138
lbl137:
                // 1 sources

                return var42_5;
lbl138:
                // 1 sources

                ** GOTO lbl152
            }
            case 3: {
                updatedChamps = (List)$continuation.L$7;
                currentChamps = (List)$continuation.L$6;
                allNames = (Set)$continuation.L$5;
                combinedRoles = (Map)$continuation.L$4;
                combinedTiers = (Map)$continuation.L$3;
                wrfData = (Map)$continuation.L$2;
                bbwrData = (Map)$continuation.L$1;
                context = (Context)$continuation.L$0;
                try {
                    ResultKt.throwOnFailure((Object)$result);
                    v11 = $result;
lbl152:
                    // 2 sources

                    AppLogger.INSTANCE.d("BestBuildWrScraper", "Global Tier List synced from BBWR and WRF");
                    BestBuildWrScraper._syncState.setValue((Object)new BestBuildSyncState.Success(updatedChamps.size(), "BBWR + WildRiftFire", System.currentTimeMillis()));
                    var3_7 = true;
                }
                catch (Exception e) {
                    if (e instanceof CancellationException) {
                        throw e;
                    }
                    AppLogger.INSTANCE.e("BestBuildWrScraper", "Error syncing BBWR/WRF tierlist", e);
                    v12 = e.getLocalizedMessage();
                    if (v12 == null) {
                        v12 = "Unknown error";
                    }
                    BestBuildWrScraper._syncState.setValue((Object)new BestBuildSyncState.Error(v12));
                    var3_7 = false;
                }
                return Boxing.boxBoolean((boolean)(var3_7 != false));
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    private final Object fetchBbwrTiers(Continuation<? super Map<String, ? extends Pair<String, ? extends Set<LaneRole>>>> $completion) {
        if (!($completion instanceof fetchBbwrTiers.1)) ** GOTO lbl-1000
        var28_2 = $completion;
        if ((var28_2.label & -2147483648) != 0) {
            var28_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ BestBuildWrScraper this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return BestBuildWrScraper.access$fetchBbwrTiers(this.this$0, (Continuation)this);
                }
            };
        }
        $result = $continuation.result;
        var29_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                champData = new LinkedHashMap<K, V>();
                $continuation.L$0 = champData;
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super String>, Object>(null){
                    int label;

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    public final Object invokeSuspend(Object $result) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                Object object;
                                ResultKt.throwOnFailure((Object)$result);
                                Request request = new Request.Builder().url("https://bestbuildwr.com/tierlist").build();
                                Closeable closeable = (Closeable)BestBuildWrScraper.access$getHttpClient(BestBuildWrScraper.INSTANCE).newCall(request).execute();
                                Throwable throwable = null;
                                try {
                                    Response response = (Response)closeable;
                                    boolean bl = false;
                                    if (!response.isSuccessful()) {
                                        String string = "";
                                        return string;
                                    }
                                    Object object2 = response.body();
                                    if (object2 == null || (object2 = object2.string()) == null) {
                                        object2 = "";
                                    }
                                    object = object2;
                                }
                                catch (Throwable throwable2) {
                                    throwable = throwable2;
                                    throw throwable2;
                                }
                                finally {
                                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                                }
                                return object;
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super String> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                ** if (v0 != var29_4) goto lbl20
lbl19:
                // 1 sources

                return var29_4;
lbl20:
                // 1 sources

                ** GOTO lbl27
            }
            case 1: {
                champData = (Map)$continuation.L$0;
                try {
                    ResultKt.throwOnFailure((Object)$result);
                    v0 = $result;
lbl27:
                    // 2 sources

                    if (((CharSequence)(html = (String)v0)).length() == 0) {
                        return champData;
                    }
                    regex = new Regex("<script id=\"__NEXT_DATA__\" type=\"application/json\">(.*?)</script>");
                    matchResult = Regex.find$default((Regex)regex, (CharSequence)html, (int)0, (int)2, null);
                    if (matchResult == null) ** GOTO lbl123
                    jsonString = (String)matchResult.getGroupValues().get(1);
                    jsonObject = new JSONObject(jsonString);
                    var9_12 = jsonObject.optJSONObject("props");
                    v1 = championsArray = var9_12 != null && (var10_13 = var9_12.optJSONObject("pageProps")) != null && (var11_15 = var10_13.optJSONObject("tierData")) != null ? var11_15.optJSONArray("champions") : null;
                    if (championsArray == null) ** GOTO lbl123
                    var10_13 = new String[]{"Top", "Jungla", "Mid", "ADC", "Support"};
                    validLabels = CollectionsKt.listOf((Object[])var10_13);
                    var11_16 = championsArray.length();
                    block21: for (i = 0; i < var11_16; ++i) {
                        if (championsArray.optJSONObject(i) == null || !validLabels.contains(label = categoryObj.optString("label")) || (var15_21 = label) == null) continue;
                        tmp = -1;
                        switch (var15_21.hashCode()) {
                            case 64640: {
                                if (var15_21.equals("ADC")) {
                                    tmp = 1;
                                }
                                break;
                            }
                            case -2064978727: {
                                if (var15_21.equals("Jungla")) {
                                    tmp = 2;
                                }
                                break;
                            }
                            case 84277: {
                                if (var15_21.equals("Top")) {
                                    tmp = 3;
                                }
                                break;
                            }
                            case -190113873: {
                                if (var15_21.equals("Support")) {
                                    tmp = 4;
                                }
                                break;
                            }
                            case 77352: {
                                if (var15_21.equals("Mid")) {
                                    tmp = 5;
                                }
                                break;
                            }
                        }
                        switch (tmp) {
                            case 3: {
                                v2 = LaneRole.TOP;
                                break;
                            }
                            case 2: {
                                v2 = LaneRole.JUNGLE;
                                break;
                            }
                            case 5: {
                                v2 = LaneRole.MID;
                                break;
                            }
                            case 1: {
                                v2 = LaneRole.ADC;
                                break;
                            }
                            case 4: {
                                v2 = LaneRole.SUPPORT;
                                break;
                            }
                            default: {
                                continue block21;
                            }
                        }
                        mappedRole = v2;
                        if (categoryObj.optJSONObject("tiers") == null) continue;
                        keys = tiersObj.keys();
                        while (keys.hasNext()) {
                            tierKey = (String)keys.next();
                            if (tiersObj.optJSONArray(tierKey) == null) continue;
                            var20_26 = champsInTier.length();
                            for (j = 0; j < var20_26; ++j) {
                                if (champsInTier.optJSONObject(j) == null) continue;
                                v3 = champObj.optString("name");
                                Intrinsics.checkNotNullExpressionValue((Object)v3, (String)"optString(...)");
                                Intrinsics.checkNotNullExpressionValue((Object)v3.toUpperCase(Locale.ROOT), (String)"toUpperCase(...)");
                                normalizedName = StringsKt.replace$default((String)StringsKt.replace$default((String)StringsKt.replace$default((String)rawName, (String)"'", (String)"", (boolean)false, (int)4, null), (String)" ", (String)"", (boolean)false, (int)4, null), (String)".", (String)"", (boolean)false, (int)4, null);
                                existing = (Pair)champData.get(normalizedName);
                                if (existing == null) {
                                    var25_31 = champData;
                                    var26_32 = new Pair[]{mappedRole};
                                    var26_32 = new Pair((Object)tierKey, (Object)SetsKt.mutableSetOf((Object[])var26_32));
                                    var25_31.put(normalizedName, var26_32);
                                    continue;
                                }
                                ((Set)existing.getSecond()).add(mappedRole);
                                Intrinsics.checkNotNull((Object)tierKey);
                                if (!this.isHigherTier(tierKey, (String)existing.getFirst())) continue;
                                champData.put(normalizedName, new Pair((Object)tierKey, existing.getSecond()));
                            }
                        }
                    }
                }
                catch (Exception e) {
                    if (e instanceof CancellationException) {
                        throw e;
                    }
                    AppLogger.INSTANCE.e("BestBuildWrScraper", "Failed to fetch BBWR", e);
                }
lbl123:
                // 4 sources

                return champData;
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Unable to fully structure code
     */
    private final Object fetchWrfTiers(Continuation<? super Map<String, ? extends Pair<String, ? extends Set<LaneRole>>>> $completion) {
        if (!($completion instanceof fetchWrfTiers.1)) ** GOTO lbl-1000
        var22_2 = $completion;
        if ((var22_2.label & -2147483648) != 0) {
            var22_2.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                /* synthetic */ Object result;
                final /* synthetic */ BestBuildWrScraper this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return BestBuildWrScraper.access$fetchWrfTiers(this.this$0, (Continuation)this);
                }
            };
        }
        $result = $continuation.result;
        var23_4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                champData = new LinkedHashMap<K, V>();
                $continuation.L$0 = champData;
                $continuation.label = 1;
                v0 = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super String>, Object>(null){
                    int label;

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    public final Object invokeSuspend(Object $result) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                Object object;
                                ResultKt.throwOnFailure((Object)$result);
                                Request request = new Request.Builder().url("https://www.wildriftfire.com/tier-list").build();
                                Closeable closeable = (Closeable)BestBuildWrScraper.access$getHttpClient(BestBuildWrScraper.INSTANCE).newCall(request).execute();
                                Throwable throwable = null;
                                try {
                                    Response response = (Response)closeable;
                                    boolean bl = false;
                                    if (!response.isSuccessful()) {
                                        String string = "";
                                        return string;
                                    }
                                    Object object2 = response.body();
                                    if (object2 == null || (object2 = object2.string()) == null) {
                                        object2 = "";
                                    }
                                    object = object2;
                                }
                                catch (Throwable throwable2) {
                                    throwable = throwable2;
                                    throw throwable2;
                                }
                                finally {
                                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                                }
                                return object;
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super String> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (Continuation)$continuation);
                ** if (v0 != var23_4) goto lbl20
lbl19:
                // 1 sources

                return var23_4;
lbl20:
                // 1 sources

                ** GOTO lbl27
            }
            case 1: {
                champData = (Map)$continuation.L$0;
                try {
                    ResultKt.throwOnFailure((Object)$result);
                    v0 = $result;
lbl27:
                    // 2 sources

                    if (((CharSequence)(html = (String)v0)).length() == 0) {
                        return champData;
                    }
                    v1 = Jsoup.parse((String)html);
                    Intrinsics.checkNotNullExpressionValue((Object)v1, (String)"parse(...)");
                    document = v1;
                    v2 = document.select(".wf-tier-list__tiers__block");
                    Intrinsics.checkNotNullExpressionValue((Object)v2, (String)"select(...)");
                    blocks = v2;
                    v3 = blocks.iterator();
                    Intrinsics.checkNotNullExpressionValue((Object)v3, (String)"iterator(...)");
                    var6_10 = v3;
                    while (var6_10.hasNext()) {
                        v4 = var6_10.next();
                        Intrinsics.checkNotNullExpressionValue(v4, (String)"next(...)");
                        block = (Element)v4;
                        if (block.selectFirst(".tier") == null) continue;
                        v5 = tierDiv.className();
                        Intrinsics.checkNotNullExpressionValue((Object)v5, (String)"className(...)");
                        tierClass = StringsKt.trim((CharSequence)StringsKt.replace$default((String)v5, (String)"tier", (String)"", (boolean)false, (int)4, null)).toString();
                        if (StringsKt.contains$default((CharSequence)tierClass, (CharSequence)"splus", (boolean)false, (int)2, null)) {
                            v6 = "S+";
                        } else if (StringsKt.contains$default((CharSequence)tierClass, (CharSequence)"s", (boolean)false, (int)2, null)) {
                            v6 = "S";
                        } else if (StringsKt.contains$default((CharSequence)tierClass, (CharSequence)"a", (boolean)false, (int)2, null)) {
                            v6 = "A";
                        } else if (StringsKt.contains$default((CharSequence)tierClass, (CharSequence)"b", (boolean)false, (int)2, null)) {
                            v6 = "B";
                        } else {
                            if (!StringsKt.contains$default((CharSequence)tierClass, (CharSequence)"c", (boolean)false, (int)2, null)) continue;
                            v6 = "C";
                        }
                        mappedTier = v6;
                        Intrinsics.checkNotNullExpressionValue((Object)block.select("a.ico-holder"), (String)"select(...)");
                        Intrinsics.checkNotNullExpressionValue((Object)links.iterator(), (String)"iterator(...)");
                        while (var12_16.hasNext()) {
                            v7 = var12_16.next();
                            Intrinsics.checkNotNullExpressionValue(v7, (String)"next(...)");
                            link = (Element)v7;
                            if (link.selectFirst("span:not(.tier-delta)") == null) continue;
                            Intrinsics.checkNotNullExpressionValue((Object)nameSpan.text(), (String)"text(...)");
                            v8 = rawName.toUpperCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue((Object)v8, (String)"toUpperCase(...)");
                            normalizedName = StringsKt.replace$default((String)StringsKt.replace$default((String)StringsKt.replace$default((String)v8, (String)"'", (String)"", (boolean)false, (int)4, null), (String)" ", (String)"", (boolean)false, (int)4, null), (String)".", (String)"", (boolean)false, (int)4, null);
                            Intrinsics.checkNotNullExpressionValue((Object)link.attr("data-role"), (String)"attr(...)");
                            var19_23 = rawRole;
                            tmp = -1;
                            switch (var19_23.hashCode()) {
                                case -190113873: {
                                    if (var19_23.equals("Support")) {
                                        tmp = 1;
                                    }
                                    break;
                                }
                                case -2064978723: {
                                    if (var19_23.equals("Jungle")) {
                                        tmp = 2;
                                    }
                                    break;
                                }
                                case 2582783: {
                                    if (var19_23.equals("Solo")) {
                                        tmp = 3;
                                    }
                                    break;
                                }
                                case 77352: {
                                    if (var19_23.equals("Mid")) {
                                        tmp = 4;
                                    }
                                    break;
                                }
                                case 69086: {
                                    if (var19_23.equals("Duo")) {
                                        tmp = 5;
                                    }
                                    break;
                                }
                            }
                            switch (tmp) {
                                case 3: {
                                    v9 = LaneRole.TOP;
                                    break;
                                }
                                case 2: {
                                    v9 = LaneRole.JUNGLE;
                                    break;
                                }
                                case 4: {
                                    v9 = LaneRole.MID;
                                    break;
                                }
                                case 5: {
                                    v9 = LaneRole.ADC;
                                    break;
                                }
                                case 1: {
                                    v9 = LaneRole.SUPPORT;
                                    break;
                                }
                                default: {
                                    v9 = null;
                                }
                            }
                            mappedRole = v9;
                            existing = (Pair)champData.get(normalizedName);
                            if (existing == null) {
                                roles = new LinkedHashSet<E>();
                                if (mappedRole != null) {
                                    roles.add(mappedRole);
                                }
                                champData.put(normalizedName, new Pair((Object)mappedTier, (Object)roles));
                                continue;
                            }
                            if (mappedRole != null) {
                                ((Set)existing.getSecond()).add(mappedRole);
                            }
                            if (!this.isHigherTier(mappedTier, (String)existing.getFirst())) continue;
                            champData.put(normalizedName, new Pair((Object)mappedTier, existing.getSecond()));
                        }
                    }
                }
                catch (Exception e) {
                    if (e instanceof CancellationException) {
                        throw e;
                    }
                    AppLogger.INSTANCE.e("BestBuildWrScraper", "Failed to fetch WRF", e);
                }
                return champData;
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final int getTierValue(String tier) {
        String string = tier;
        if (string == null) return -1;
        int n = -1;
        switch (string.hashCode()) {
            case 65: {
                if (string.equals("A")) {
                    n = 1;
                }
                break;
            }
            case 66: {
                if (string.equals("B")) {
                    n = 2;
                }
                break;
            }
            case 83: {
                if (string.equals("S")) {
                    n = 3;
                }
                break;
            }
            case 67: {
                if (string.equals("C")) {
                    n = 4;
                }
                break;
            }
            case 68: {
                if (string.equals("D")) {
                    n = 5;
                }
                break;
            }
            case 2616: {
                if (string.equals("S+")) {
                    n = 6;
                }
                break;
            }
            case 2058: {
                if (string.equals("A+")) {
                    n = 7;
                }
                break;
            }
        }
        switch (n) {
            case 6: {
                return 6;
            }
            case 3: {
                return 5;
            }
            case 7: {
                return 4;
            }
            case 1: {
                return 3;
            }
            case 2: {
                return 2;
            }
            case 4: {
                return 1;
            }
            case 5: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }

    private final String getTierFromValue(int value) {
        String string;
        switch (value) {
            case 6: {
                string = "S+";
                break;
            }
            case 5: {
                string = "S";
                break;
            }
            case 4: {
                string = "A+";
                break;
            }
            case 3: {
                string = "A";
                break;
            }
            case 2: {
                string = "B";
                break;
            }
            case 1: {
                string = "C";
                break;
            }
            case 0: {
                string = "D";
                break;
            }
            default: {
                string = "C";
            }
        }
        return string;
    }

    private final boolean isHigherTier(String newTier, String oldTier) {
        return this.getTierValue(newTier) > this.getTierValue(oldTier);
    }

    private static final OkHttpClient httpClient_delegate$lambda$0() {
        return new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).followRedirects(true).build();
    }

    public static final /* synthetic */ Object access$fetchBbwrTiers(BestBuildWrScraper $this, Continuation $completion) {
        return $this.fetchBbwrTiers((Continuation<? super Map<String, ? extends Pair<String, ? extends Set<LaneRole>>>>)$completion);
    }

    public static final /* synthetic */ OkHttpClient access$getHttpClient(BestBuildWrScraper $this) {
        return $this.getHttpClient();
    }

    public static final /* synthetic */ Object access$fetchWrfTiers(BestBuildWrScraper $this, Continuation $completion) {
        return $this.fetchWrfTiers((Continuation<? super Map<String, ? extends Pair<String, ? extends Set<LaneRole>>>>)$completion);
    }
}
