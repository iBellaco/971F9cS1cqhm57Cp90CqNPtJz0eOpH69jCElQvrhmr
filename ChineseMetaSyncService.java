/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.util.Log
 *  androidx.compose.runtime.internal.StabilityInferred
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Pair
 *  kotlin.ResultKt
 *  kotlin.Triple
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.coroutines.Continuation
 *  kotlin.coroutines.CoroutineContext
 *  kotlin.coroutines.intrinsics.IntrinsicsKt
 *  kotlin.coroutines.jvm.internal.Boxing
 *  kotlin.coroutines.jvm.internal.ContinuationImpl
 *  kotlin.coroutines.jvm.internal.SpillingKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
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
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package com.example.data.sync;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.compose.runtime.internal.StabilityInferred;
import com.example.data.WildRiftRepository;
import com.example.data.local.WildRiftLocalCache;
import com.example.data.sync.BestBuildWrScraper;
import com.example.data.sync.ChineseMetaSyncService;
import com.example.data.sync.ChineseSyncState;
import com.example.data.sync.TencentRankTier;
import com.example.model.Champion;
import com.example.model.LaneRole;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
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
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(mv={2, 2, 0}, k=1, xi=48, d1={"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0001:B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050%2\u0006\u0010\u001a\u001a\u00020\u001bJ*\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010'\u001a\u00020!2\b\b\u0002\u0010(\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010*J:\u0010+\u001a,\u0012\u0004\u0012\u00020\u0005\u0012\"\u0012 \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020.0-0,0%2\u0006\u0010'\u001a\u00020!H\u0002J\u0010\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.H\u0002J\u001c\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002020,2\u0006\u00103\u001a\u00020!H\u0002J2\u00104\u001a\b\u0012\u0004\u0012\u000206052\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010'\u001a\u00020!2\n\b\u0002\u00107\u001a\u0004\u0018\u000108H\u0086@\u00a2\u0006\u0002\u00109R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014\u00a8\u0006;"}, d2={"Lcom/example/data/sync/ChineseMetaSyncService;", "", "<init>", "()V", "TAG", "", "PREFS_NAME", "KEY_LAST_SYNC", "KEY_SELECTED_TIER", "KEY_YESTERDAY_PREFIX", "CN_API_URL_HERO_LIST", "CN_API_URL_RANK", "httpClient", "Lokhttp3/OkHttpClient;", "_syncState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/data/sync/ChineseSyncState;", "syncState", "Lkotlinx/coroutines/flow/StateFlow;", "getSyncState", "()Lkotlinx/coroutines/flow/StateFlow;", "_currentRegion", "currentRegion", "getCurrentRegion", "loadRegion", "", "context", "Landroid/content/Context;", "setRegion", "region", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "_currentTier", "Lcom/example/data/sync/TencentRankTier;", "currentTier", "getCurrentTier", "getLastSyncInfo", "Lkotlin/Pair;", "syncChineseMeta", "targetTier", "forceRefresh", "", "(Landroid/content/Context;Lcom/example/data/sync/TencentRankTier;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAndParseTencentLiveStats", "", "Lkotlin/Triple;", "", "roundTwoDecimals", "value", "generateChineseStatsSnapshot", "Lcom/example/data/sync/ChineseMetaSyncService$CnChampionStat;", "tier", "getFilteredRankings", "", "Lcom/example/model/Champion;", "lane", "Lcom/example/model/LaneRole;", "(Landroid/content/Context;Lcom/example/data/sync/TencentRankTier;Lcom/example/model/LaneRole;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CnChampionStat", "app"})
@StabilityInferred(parameters=0)
@SourceDebugExtension(value={"SMAP\nChineseMetaSyncService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChineseMetaSyncService.kt\ncom/example/data/sync/ChineseMetaSyncService\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,556:1\n774#2:557\n865#2,2:558\n1068#2:560\n*S KotlinDebug\n*F\n+ 1 ChineseMetaSyncService.kt\ncom/example/data/sync/ChineseMetaSyncService\n*L\n550#1:557\n550#1:558,2\n553#1:560\n*E\n"})
public final class ChineseMetaSyncService {
    @NotNull
    public static final ChineseMetaSyncService INSTANCE = new ChineseMetaSyncService();
    @NotNull
    private static final String TAG = "ChineseMetaSyncService";
    @NotNull
    private static final String PREFS_NAME = "cn_wild_rift_stats_cache";
    @NotNull
    private static final String KEY_LAST_SYNC = "cn_last_sync_timestamp";
    @NotNull
    private static final String KEY_SELECTED_TIER = "cn_selected_tier";
    @NotNull
    private static final String KEY_YESTERDAY_PREFIX = "cn_yesterday_champ_";
    @NotNull
    private static final String CN_API_URL_HERO_LIST = "https://game.gtimg.cn/images/lgamem/act/lrlib/js/heroList/hero_list.js";
    @NotNull
    private static final String CN_API_URL_RANK = "https://mlol.qt.qq.com/go/lgame_battle_info/hero_rank_list_v2";
    @NotNull
    private static final OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).build();
    @NotNull
    private static final MutableStateFlow<ChineseSyncState> _syncState = StateFlowKt.MutableStateFlow((Object)ChineseSyncState.Idle.INSTANCE);
    @NotNull
    private static final StateFlow<ChineseSyncState> syncState = FlowKt.asStateFlow(_syncState);
    @NotNull
    private static final MutableStateFlow<String> _currentRegion = StateFlowKt.MutableStateFlow((Object)"Global");
    @NotNull
    private static final StateFlow<String> currentRegion = FlowKt.asStateFlow(_currentRegion);
    @NotNull
    private static final MutableStateFlow<TencentRankTier> _currentTier = StateFlowKt.MutableStateFlow((Object)((Object)TencentRankTier.DIAMOND_PLUS));
    @NotNull
    private static final StateFlow<TencentRankTier> currentTier = FlowKt.asStateFlow(_currentTier);
    public static final int $stable = 8;

    private ChineseMetaSyncService() {
    }

    @NotNull
    public final StateFlow<ChineseSyncState> getSyncState() {
        return syncState;
    }

    @NotNull
    public final StateFlow<String> getCurrentRegion() {
        return currentRegion;
    }

    public final void loadRegion(@NotNull Context context) {
        String loaded;
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String string = prefs.getString("META_REGION", "Global");
        if (string == null) {
            string = "Global";
        }
        _currentRegion.setValue((Object)(Intrinsics.areEqual((Object)(loaded = string), (Object)"BestBuildWR") ? "Global" : loaded));
    }

    public final void setRegion(@NotNull Context context, @NotNull String region, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        Intrinsics.checkNotNullParameter((Object)region, (String)"region");
        Intrinsics.checkNotNullParameter((Object)coroutineScope, (String)"coroutineScope");
        String normalizedRegion = Intrinsics.areEqual((Object)region, (Object)"BestBuildWR") ? "Global" : region;
        _currentRegion.setValue((Object)normalizedRegion);
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        prefs.edit().putString("META_REGION", normalizedRegion).apply();
        int n = -1;
        switch (normalizedRegion.hashCode()) {
            case 2483: {
                if (normalizedRegion.equals("NA")) {
                    n = 1;
                }
                break;
            }
            case -508399835: {
                if (normalizedRegion.equals("BestBuildWR")) {
                    n = 2;
                }
                break;
            }
            case 2135814083: {
                if (normalizedRegion.equals("Global")) {
                    n = 2;
                }
                break;
            }
        }
        switch (n) {
            case 1: {
                WildRiftRepository.INSTANCE.initChampions(context, true);
                _syncState.setValue((Object)ChineseSyncState.Idle.INSTANCE);
                Unit unit = Unit.INSTANCE;
                break;
            }
            case 2: {
                Unit unit = BuildersKt.launch$default((CoroutineScope)coroutineScope, null, null, (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Unit>, Object>(context, null){
                    int label;
                    final /* synthetic */ Context $context;
                    {
                        this.$context = $context;
                        super(2, $completion);
                    }

                    /*
                     * Unable to fully structure code
                     */
                    public final Object invokeSuspend(Object $result) {
                        var3_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                WildRiftRepository.INSTANCE.initChampions(this.$context, true);
                                ChineseMetaSyncService.access$get_syncState$p().setValue((Object)ChineseSyncState.Syncing.INSTANCE);
                                this.label = 1;
                                v0 = BestBuildWrScraper.INSTANCE.syncGlobalTierList(this.$context, (Continuation<? super Boolean>)((Continuation)this));
                                if (v0 == var3_2) {
                                    return var3_2;
                                }
                                ** GOTO lbl15
                            }
                            case 1: {
                                ResultKt.throwOnFailure((Object)$result);
                                v0 = $result;
lbl15:
                                // 2 sources

                                if (success = ((Boolean)v0).booleanValue()) {
                                    ChineseMetaSyncService.access$get_syncState$p().setValue((Object)new ChineseSyncState.Success(TencentRankTier.DIAMOND_PLUS, WildRiftRepository.INSTANCE.getChampions().size(), "Reciente", "Global Meta", false));
                                } else {
                                    ChineseMetaSyncService.access$get_syncState$p().setValue((Object)new ChineseSyncState.Error("Fallo al sincronizar Meta Global"));
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (int)3, null);
                break;
            }
            default: {
                Unit unit = BuildersKt.launch$default((CoroutineScope)coroutineScope, null, null, (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Unit>, Object>(context, null){
                    int label;
                    final /* synthetic */ Context $context;
                    {
                        this.$context = $context;
                        super(2, $completion);
                    }

                    /*
                     * Enabled force condition propagation
                     * Lifted jumps to return sites
                     */
                    public final Object invokeSuspend(Object $result) {
                        Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0: {
                                ResultKt.throwOnFailure((Object)$result);
                                this.label = 1;
                                Object object2 = ChineseMetaSyncService.INSTANCE.syncChineseMeta(this.$context, (TencentRankTier)((Object)ChineseMetaSyncService.access$get_currentTier$p().getValue()), true, (Continuation<? super Unit>)((Continuation)this));
                                if (object2 != object) return Unit.INSTANCE;
                                return object;
                            }
                            case 1: {
                                ResultKt.throwOnFailure((Object)$result);
                                Object object2 = $result;
                                return Unit.INSTANCE;
                            }
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (Continuation)new /* invalid duplicate definition of identical inner class */;
                    }

                    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> p2) {
                        return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                    }
                }), (int)3, null);
            }
        }
    }

    @NotNull
    public final StateFlow<TencentRankTier> getCurrentTier() {
        return currentTier;
    }

    @NotNull
    public final Pair<String, String> getLastSyncInfo(@NotNull Context context) {
        Object object;
        TencentRankTier tencentRankTier;
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String tierCode = prefs.getString(KEY_SELECTED_TIER, "DIAMOND_PLUS");
        try {
            String string = tierCode;
            if (string == null) {
                string = "DIAMOND_PLUS";
            }
            tencentRankTier = TencentRankTier.valueOf(string);
        }
        catch (Exception e) {
            tencentRankTier = TencentRankTier.DIAMOND_PLUS;
        }
        TencentRankTier tier = tencentRankTier;
        String lastTime = prefs.getString(KEY_LAST_SYNC, null);
        if (lastTime != null) {
            object = lastTime;
        } else {
            SimpleDateFormat simpleDateFormat;
            SimpleDateFormat simpleDateFormat2 = simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            boolean bl = false;
            simpleDateFormat2.setTimeZone(TimeZone.getDefault());
            SimpleDateFormat nowFormat = simpleDateFormat;
            object = nowFormat.format(new Date()) + " (" + tier.getDisplayName() + ")";
        }
        String formattedTime = object;
        return new Pair((Object)tier.getDisplayName(), (Object)formattedTime);
    }

    @Nullable
    public final Object syncChineseMeta(@NotNull Context context, @NotNull TencentRankTier targetTier, boolean forceRefresh, @NotNull Continuation<? super Unit> $completion) {
        _syncState.setValue((Object)ChineseSyncState.Syncing.INSTANCE);
        _currentTier.setValue((Object)targetTier);
        Object object = BuildersKt.withContext((CoroutineContext)((CoroutineContext)Dispatchers.getIO()), (Function2)((Function2)new Function2<CoroutineScope, Continuation<? super Object>, Object>(context, targetTier, null){
            int label;
            final /* synthetic */ Context $context;
            final /* synthetic */ TencentRankTier $targetTier;
            {
                this.$context = $context;
                this.$targetTier = $targetTier;
                super(2, $completion);
            }

            /*
             * Unable to fully structure code
             */
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure((Object)$result);
                        try {
                            prefs = this.$context.getSharedPreferences("cn_wild_rift_stats_cache", 0);
                            onlineDataFetched = false;
                            sourceUsed = "Tencent LOLM China (Live API V2)";
                            parsedDataList = null;
                            dtStatDate = null;
                            try {
                                pair = ChineseMetaSyncService.access$fetchAndParseTencentLiveStats(ChineseMetaSyncService.INSTANCE, this.$targetTier);
                                dtStatDate = (String)pair.getFirst();
                                parsedDataList = (Map)pair.getSecond();
                                if (parsedDataList.isEmpty() == false) {
                                    onlineDataFetched = true;
                                }
                            }
                            catch (Exception netEx) {
                                Log.w((String)"ChineseMetaSyncService", (String)"Fallo al consultar API oficial en vivo. Usando snapshot local.", (Throwable)netEx);
                            }
                            cnStatsSnapshot = ChineseMetaSyncService.access$generateChineseStatsSnapshot(ChineseMetaSyncService.INSTANCE, this.$targetTier);
                            finalStats = new LinkedHashMap<K, V>();
                            for (Champion champ : WildRiftRepository.INSTANCE.getChampions()) {
                                v0 = champ.getId().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue((Object)v0, (String)"toLowerCase(...)");
                                v1 = (CnChampionStat)cnStatsSnapshot.get(v0);
                                if (v1 == null) {
                                    v2 = champ.getName().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue((Object)v2, (String)"toLowerCase(...)");
                                    v1 = fallback = (CnChampionStat)cnStatsSnapshot.get(v2);
                                }
                                if ((v3 = parsedDataList) == null) ** GOTO lbl38
                                v4 = champ.getId().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue((Object)v4, (String)"toLowerCase(...)");
                                if ((v3 = (Triple)v3.get(v4)) != null) ** GOTO lbl45
lbl38:
                                // 2 sources

                                v5 = parsedDataList;
                                if (v5 != null) {
                                    v6 = champ.getName().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue((Object)v6, (String)"toLowerCase(...)");
                                    v3 = (Triple)v5.get(v6);
                                } else {
                                    v3 = null;
                                }
lbl45:
                                // 3 sources

                                if ((live = v3) != null && fallback != null) {
                                    v7 = champ.getId().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue((Object)v7, (String)"toLowerCase(...)");
                                    finalStats.put(v7, CnChampionStat.copy$default((CnChampionStat)fallback, ((Number)live.getFirst()).doubleValue(), ((Number)live.getSecond()).doubleValue(), ((Number)live.getThird()).doubleValue(), 0.0, 0.0, 0.0, null, 120, null));
                                    continue;
                                }
                                if (live != null) {
                                    v8 = champ.getId().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue((Object)v8, (String)"toLowerCase(...)");
                                    finalStats.put(v8, new CnChampionStat(((Number)live.getFirst()).doubleValue(), ((Number)live.getSecond()).doubleValue(), ((Number)live.getThird()).doubleValue(), 0.0, 0.0, 0.0, "T2"));
                                    continue;
                                }
                                if (fallback == null) continue;
                                v9 = champ.getId().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue((Object)v9, (String)"toLowerCase(...)");
                                finalStats.put(v9, fallback);
                            }
                            editor = prefs.edit();
                            $this$invokeSuspend_u24lambda_u240\1 = fallback = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                            $i$a$-apply-ChineseMetaSyncService$syncChineseMeta$2$nowFormat$1\1\196\0 = false;
                            $this$invokeSuspend_u24lambda_u240\1.setTimeZone(TimeZone.getDefault());
                            nowFormat = fallback;
                            $this$invokeSuspend_u24lambda_u240\1 = dtStatDate;
                            if (!($this$invokeSuspend_u24lambda_u240\1 == null || $this$invokeSuspend_u24lambda_u240\1.length() == 0)) {
                                v10 = dtStatDate;
                                Intrinsics.checkNotNull((Object)v10);
                                v11 = v10.substring(0, 4);
                                Intrinsics.checkNotNullExpressionValue((Object)v11, (String)"substring(...)");
                                v12 = dtStatDate.substring(4, 6);
                                Intrinsics.checkNotNullExpressionValue((Object)v12, (String)"substring(...)");
                                v13 = dtStatDate.substring(6, 8);
                                Intrinsics.checkNotNullExpressionValue((Object)v13, (String)"substring(...)");
                                v14 = v11 + "-" + v12 + "-" + v13 + " (API)";
                            } else {
                                v14 = nowFormat.format(new Date());
                            }
                            nowTimestamp = v14;
                            $this$map\2 = (Iterable)WildRiftRepository.INSTANCE.getChampions();
                            $i$f$map\2\202 = false;
                            var15_19 = $this$map\2;
                            destination\3 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map\2, (int)10));
                            $i$f$mapTo\3\557 = false;
                            for (T item\3 : $this$mapTo\3) {
                                var20_24 = (Champion)item\3;
                                var44_39 = destination\3;
                                $i$a$-map-ChineseMetaSyncService$syncChineseMeta$2$updatedChampions$1\4\559\0 = false;
                                v15 = champ\4.getId().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue((Object)v15, (String)"toLowerCase(...)");
                                stat\4 = (CnChampionStat)finalStats.get(v15);
                                if (stat\4 != null) {
                                    currentWinrate\4 = ChineseMetaSyncService.access$roundTwoDecimals(ChineseMetaSyncService.INSTANCE, stat\4.getWinRate());
                                    currentPickRate\4 = ChineseMetaSyncService.access$roundTwoDecimals(ChineseMetaSyncService.INSTANCE, stat\4.getPickRate());
                                    currentBanRate\4 = ChineseMetaSyncService.access$roundTwoDecimals(ChineseMetaSyncService.INSTANCE, stat\4.getBanRate());
                                    cnTier\4 = stat\4.getCnTier();
                                    yesterdayKey\4 = "cn_yesterday_champ_" + champ\4.getId();
                                    yesterdayWinrate\4 = prefs.getFloat(yesterdayKey\4 + "_win", (float)(currentWinrate\4 - stat\4.getDefaultWinDelta()));
                                    yesterdayPickRate\4 = prefs.getFloat(yesterdayKey\4 + "_pick", (float)(currentPickRate\4 - stat\4.getDefaultPickDelta()));
                                    yesterdayBanRate\4 = prefs.getFloat(yesterdayKey\4 + "_ban", (float)(currentBanRate\4 - stat\4.getDefaultBanDelta()));
                                    deltaWin\4 = ChineseMetaSyncService.access$roundTwoDecimals(ChineseMetaSyncService.INSTANCE, currentWinrate\4 - yesterdayWinrate\4);
                                    deltaPick\4 = ChineseMetaSyncService.access$roundTwoDecimals(ChineseMetaSyncService.INSTANCE, currentPickRate\4 - yesterdayPickRate\4);
                                    deltaBan\4 = ChineseMetaSyncService.access$roundTwoDecimals(ChineseMetaSyncService.INSTANCE, currentBanRate\4 - yesterdayBanRate\4);
                                    editor.putFloat(yesterdayKey\4 + "_win", (float)currentWinrate\4);
                                    editor.putFloat(yesterdayKey\4 + "_pick", (float)currentPickRate\4);
                                    editor.putFloat(yesterdayKey\4 + "_ban", (float)currentBanRate\4);
                                    var43_38 = currentWinrate\4 >= 53.5 && currentPickRate\4 >= 10.0 ? "S+" : (currentWinrate\4 >= 52.0 ? "S" : (currentWinrate\4 >= 50.5 ? "A+" : (currentWinrate\4 >= 49.0 ? "A" : "B")));
                                    v16 = Champion.copy$default((Champion)champ\4, null, null, null, null, null, null, null, null, null, null, null, var43_38, currentWinrate\4, currentPickRate\4, currentBanRate\4, deltaWin\4, deltaPick\4, deltaBan\4, cnTier\4, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, -522241, 16383, null);
                                } else {
                                    v16 = champ\4;
                                }
                                var44_39.add(v16);
                            }
                            updatedChampions = (List)destination\3;
                            editor.putString("cn_last_sync_timestamp", nowTimestamp + " (" + this.$targetTier.getDisplayName() + ")");
                            editor.putString("cn_selected_tier", this.$targetTier.name());
                            editor.apply();
                            WildRiftRepository.INSTANCE.getChampions().clear();
                            WildRiftRepository.INSTANCE.getChampions().addAll((Collection)updatedChampions);
                            try {
                                WildRiftLocalCache.saveToLocalCache$default(WildRiftLocalCache.INSTANCE, this.$context, null, updatedChampions, null, null, null, null, 122, null);
                                Log.d((String)"ChineseMetaSyncService", (String)"Estad\u00edsticas guardadas exitosamente en Cach\u00e9 Local.");
                            }
                            catch (Exception e) {
                                Log.e((String)"ChineseMetaSyncService", (String)("Error guardando estad\u00edsticas en BD: " + e.getMessage()), (Throwable)e);
                            }
                            v17 = ChineseMetaSyncService.access$get_syncState$p();
                            v18 = updatedChampions.size();
                            Intrinsics.checkNotNull((Object)nowTimestamp);
                            v17.setValue((Object)new ChineseSyncState.Success(this.$targetTier, v18, nowTimestamp, onlineDataFetched != false ? sourceUsed : "Meta Oficial (Snapshot Local)", true));
                            var2_2 /* !! */  = Boxing.boxInt((int)Log.d((String)"ChineseMetaSyncService", (String)("Sincronizaci\u00f3n completada. Campeones: " + updatedChampions.size())));
                        }
                        catch (Exception e) {
                            Log.e((String)"ChineseMetaSyncService", (String)"Error al sincronizar estad\u00edsticas del servidor chino", (Throwable)e);
                            v19 = ChineseMetaSyncService.access$get_syncState$p();
                            v20 = e.getLocalizedMessage();
                            if (v20 == null) {
                                v20 = "Error al conectar con servidor de datos";
                            }
                            v19.setValue((Object)new ChineseSyncState.Error(v20));
                            var2_2 /* !! */  = Unit.INSTANCE;
                        }
                        return var2_2 /* !! */ ;
                    }
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                return (Continuation)new /* invalid duplicate definition of identical inner class */;
            }

            public final Object invoke(CoroutineScope p1, Continuation<Object> p2) {
                return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
        }), $completion);
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return object;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object syncChineseMeta$default(ChineseMetaSyncService chineseMetaSyncService, Context context, TencentRankTier tencentRankTier, boolean bl, Continuation continuation, int n, Object object) {
        if ((n & 2) != 0) {
            tencentRankTier = (TencentRankTier)((Object)_currentTier.getValue());
        }
        if ((n & 4) != 0) {
            bl = false;
        }
        return chineseMetaSyncService.syncChineseMeta(context, tencentRankTier, bl, (Continuation<? super Unit>)continuation);
    }

    private final Pair<String, Map<String, Triple<Double, Double, Double>>> fetchAndParseTencentLiveStats(TencentRankTier targetTier) {
        Map heroIdToEnglishMap;
        block47: {
            JSONObject jsonRoot;
            JSONObject jSONObject;
            heroIdToEnglishMap = new LinkedHashMap();
            Request reqMetadata = new Request.Builder().url(CN_API_URL_HERO_LIST).header("User-Agent", "Mozilla/5.0").build();
            Response resMetadata = httpClient.newCall(reqMetadata).execute();
            if (!resMetadata.isSuccessful()) break block47;
            Object object = resMetadata.body();
            if (object == null || (object = object.string()) == null) {
                object = "";
            }
            Object body = object;
            try {
                jSONObject = new JSONObject((String)body).optJSONObject("heroList");
            }
            catch (Exception e) {
                String cleanBody = StringsKt.removeSuffix((String)((Object)StringsKt.trim((CharSequence)StringsKt.substringAfter$default((String)body, (String)"=", null, (int)2, null))).toString(), (CharSequence)";");
                jSONObject = new JSONObject(cleanBody).optJSONObject("heroList");
            }
            JSONObject jSONObject2 = jsonRoot = jSONObject;
            if (jSONObject2 == null) break block47;
            JSONObject jSONObject3 = jSONObject2;
            boolean bl = false;
            Iterator iterator = jSONObject3.keys();
            while (iterator.hasNext()) {
                String string;
                String string2;
                String string3 = (String)iterator.next();
                JSONObject jSONObject4 = jSONObject3.getJSONObject(string3);
                String string4 = jSONObject4.optString("heroId");
                String string5 = jSONObject4.optString("poster");
                Intrinsics.checkNotNull((Object)string5);
                if (!(((CharSequence)string5).length() > 0)) continue;
                String string6 = StringsKt.substringAfterLast$default((String)string5, (String)"/", null, (int)2, null);
                String string7 = StringsKt.substringBefore$default((String)string6, (String)"_", null, (int)2, null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue((Object)string7, (String)"toLowerCase(...)");
                switch (string2 = string7) {
                    case "monkeyking": {
                        string = "wukong";
                        break;
                    }
                    case "xinzhao": {
                        string = "xin_zhao";
                        break;
                    }
                    case "masteryi": {
                        string = "master_yi";
                        break;
                    }
                    case "twistedfate": {
                        string = "twisted_fate";
                        break;
                    }
                    case "missfortune": {
                        string = "miss_fortune";
                        break;
                    }
                    case "aurelionsol": {
                        string = "aurelion_sol";
                        break;
                    }
                    case "drmundo": {
                        string = "dr_mundo";
                        break;
                    }
                    case "jarvaniv": {
                        string = "jarvan_iv";
                        break;
                    }
                    case "leesin": {
                        string = "lee_sin";
                        break;
                    }
                    case "tahmkench": {
                        string = "tahm_kench";
                        break;
                    }
                    default: {
                        string = string2;
                    }
                }
                string2 = string;
                heroIdToEnglishMap.put(string4, string2);
            }
        }
        Request reqRank = new Request.Builder().url(CN_API_URL_RANK).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36").header("Referer", "https://lolm.qq.com/act/a20220818raider/index.html").header("Accept", "application/json, text/plain, */*").build();
        Response resRank = httpClient.newCall(reqRank).execute();
        Map resultMap = new LinkedHashMap();
        String dtStatDate = "";
        if (resRank.isSuccessful()) {
            Object object = resRank.body();
            if (object == null || (object = object.string()) == null) {
                object = "";
            }
            Object body = object;
            JSONObject jsonRoot = new JSONObject((String)body);
            JSONObject jSONObject = jsonRoot.optJSONObject("data");
            if (jSONObject == null) {
                return new Pair((Object)dtStatDate, (Object)resultMap);
            }
            JSONObject dataObj = jSONObject;
            String string = jsonRoot.optString("dtstatdate", "");
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"optString(...)");
            dtStatDate = string;
            String tierKey = targetTier.getCode();
            JSONObject jSONObject5 = dataObj.optJSONObject(tierKey);
            if (jSONObject5 == null) {
                return new Pair((Object)dtStatDate, (Object)resultMap);
            }
            JSONObject tierData = jSONObject5;
            Map totalAppearsMap = new LinkedHashMap();
            Map weightedWinRatesMap = new LinkedHashMap();
            Map banRatesMap = new LinkedHashMap();
            Iterator lanes = tierData.keys();
            while (lanes.hasNext()) {
                JSONArray laneArray;
                String lane = (String)lanes.next();
                if (tierData.optJSONArray(lane) == null) continue;
                int n = laneArray.length();
                for (int i = 0; i < n; ++i) {
                    JSONObject hObj = laneArray.getJSONObject(i);
                    String heroId = hObj.optString("hero_id");
                    String string8 = hObj.optString("appear_rate_percent");
                    Intrinsics.checkNotNullExpressionValue((Object)string8, (String)"optString(...)");
                    Double d = StringsKt.toDoubleOrNull((String)string8);
                    double appearRate = d != null ? d : 0.0;
                    String string9 = hObj.optString("win_rate_percent");
                    Intrinsics.checkNotNullExpressionValue((Object)string9, (String)"optString(...)");
                    Double d2 = StringsKt.toDoubleOrNull((String)string9);
                    double winRate = d2 != null ? d2 : 50.0;
                    String string10 = hObj.optString("forbid_rate_percent");
                    Intrinsics.checkNotNullExpressionValue((Object)string10, (String)"optString(...)");
                    Double d3 = StringsKt.toDoubleOrNull((String)string10);
                    double banRate = d3 != null ? d3 : 0.0;
                    Map map = totalAppearsMap;
                    Double d4 = (Double)totalAppearsMap.get(heroId);
                    Double d5 = (d4 != null ? d4 : 0.0) + appearRate;
                    map.put(heroId, d5);
                    map = weightedWinRatesMap;
                    Double d6 = (Double)weightedWinRatesMap.get(heroId);
                    d5 = (d6 != null ? d6 : 0.0) + winRate * appearRate;
                    map.put(heroId, d5);
                    if (banRatesMap.containsKey(heroId)) continue;
                    banRatesMap.put(heroId, banRate);
                }
            }
            for (Map.Entry entry : totalAppearsMap.entrySet()) {
                String englishName;
                double d;
                String heroId = (String)entry.getKey();
                double totalAppear = ((Number)entry.getValue()).doubleValue();
                if ((String)heroIdToEnglishMap.get(heroId) == null) continue;
                if (totalAppear > 0.0) {
                    Object v = weightedWinRatesMap.get(heroId);
                    Intrinsics.checkNotNull(v);
                    d = ((Number)v).doubleValue() / totalAppear;
                } else {
                    d = 50.0;
                }
                double finalWinRate = d;
                Double d7 = (Double)banRatesMap.get(heroId);
                double finalBanRate = d7 != null ? d7 : 0.0;
                resultMap.put(englishName, new Triple((Object)finalWinRate, (Object)totalAppear, (Object)finalBanRate));
            }
        }
        return new Pair((Object)dtStatDate, (Object)resultMap);
    }

    private final double roundTwoDecimals(double value) {
        return (double)MathKt.roundToInt((double)(value * 100.0)) / 100.0;
    }

    private final Map<String, CnChampionStat> generateChineseStatsSnapshot(TencentRankTier tier) {
        double d;
        switch (WhenMappings.$EnumSwitchMapping$0[tier.ordinal()]) {
            case 1: {
                d = 1.05;
                break;
            }
            case 2: {
                d = 1.02;
                break;
            }
            case 3: {
                d = 1.0;
                break;
            }
            case 4: {
                d = 0.98;
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        double multiplier = d;
        Pair[] pairArray = new Pair[]{TuplesKt.to((Object)"aatrox", (Object)new CnChampionStat(53.2 * multiplier, 14.5, 22.8, 0.35, 0.2, 0.8, "T0")), TuplesKt.to((Object)"ahri", (Object)new CnChampionStat(52.6 * multiplier, 16.2, 8.4, 0.15, -0.1, 0.3, "T1")), TuplesKt.to((Object)"akali", (Object)new CnChampionStat(51.8 * multiplier, 11.4, 28.5, -0.22, 0.12, 1.15, "T0")), TuplesKt.to((Object)"akshan", (Object)new CnChampionStat(52.9 * multiplier, 6.8, 5.2, 0.4, 0.05, -0.1, "T1")), TuplesKt.to((Object)"alistar", (Object)new CnChampionStat(51.4 * multiplier, 8.9, 3.8, 0.1, -0.05, 0.0, "T2")), TuplesKt.to((Object)"amumu", (Object)new CnChampionStat(52.1 * multiplier, 7.5, 4.1, -0.15, -0.2, -0.1, "T2")), TuplesKt.to((Object)"annie", (Object)new CnChampionStat(50.8 * multiplier, 4.2, 1.1, 0.05, 0.02, 0.0, "T3")), TuplesKt.to((Object)"ashe", (Object)new CnChampionStat(51.2 * multiplier, 13.8, 3.4, -0.1, 0.3, 0.05, "T2")), TuplesKt.to((Object)"aurelion_sol", (Object)new CnChampionStat(53.8 * multiplier, 5.6, 12.1, 0.62, 0.18, 0.9, "T0")), TuplesKt.to((Object)"blitzcrank", (Object)new CnChampionStat(50.2 * multiplier, 12.1, 14.6, -0.3, -0.15, 0.4, "T2")), TuplesKt.to((Object)"brand", (Object)new CnChampionStat(51.6 * multiplier, 9.4, 7.2, 0.18, 0.08, -0.05, "T2")), TuplesKt.to((Object)"braum", (Object)new CnChampionStat(52.3 * multiplier, 8.1, 2.5, 0.25, 0.1, 0.0, "T1")), TuplesKt.to((Object)"caitlyn", (Object)new CnChampionStat(51.9 * multiplier, 18.2, 16.5, 0.2, 0.45, 0.6, "T1")), TuplesKt.to((Object)"camille", (Object)new CnChampionStat(52.7 * multiplier, 9.8, 15.2, 0.3, 0.15, 0.5, "T1")), TuplesKt.to((Object)"corki", (Object)new CnChampionStat(50.9 * multiplier, 4.8, 1.8, -0.05, -0.02, 0.0, "T3")), TuplesKt.to((Object)"darius", (Object)new CnChampionStat(52.4 * multiplier, 15.1, 19.8, 0.12, 0.25, 0.45, "T1")), TuplesKt.to((Object)"diana", (Object)new CnChampionStat(52.8 * multiplier, 11.6, 9.2, 0.28, 0.14, 0.3, "T1")), TuplesKt.to((Object)"dr_mundo", (Object)new CnChampionStat(53.1 * multiplier, 10.2, 12.4, 0.42, 0.3, 0.85, "T1")), TuplesKt.to((Object)"draven", (Object)new CnChampionStat(51.5 * multiplier, 10.4, 21.0, -0.18, -0.05, 0.7, "T1")), TuplesKt.to((Object)"ekko", (Object)new CnChampionStat(52.2 * multiplier, 8.9, 6.7, 0.15, 0.1, 0.2, "T1")), TuplesKt.to((Object)"evelynn", (Object)new CnChampionStat(52.9 * multiplier, 9.1, 18.4, 0.38, 0.15, 0.95, "T1")), TuplesKt.to((Object)"ezreal", (Object)new CnChampionStat(51.4 * multiplier, 22.5, 8.9, -0.08, 0.5, 0.1, "T1")), TuplesKt.to((Object)"fiora", (Object)new CnChampionStat(53.4 * multiplier, 12.3, 24.1, 0.45, 0.22, 1.1, "T0")), TuplesKt.to((Object)"fizz", (Object)new CnChampionStat(52.0 * multiplier, 8.4, 11.2, 0.1, -0.05, 0.3, "T2")), TuplesKt.to((Object)"galio", (Object)new CnChampionStat(52.5 * multiplier, 7.8, 4.2, 0.3, 0.08, 0.1, "T1")), TuplesKt.to((Object)"garen", (Object)new CnChampionStat(51.0 * multiplier, 12.4, 3.1, -0.12, -0.1, -0.05, "T2")), TuplesKt.to((Object)"gragas", (Object)new CnChampionStat(52.1 * multiplier, 9.6, 5.4, 0.15, 0.05, 0.15, "T1")), TuplesKt.to((Object)"graves", (Object)new CnChampionStat(51.7 * multiplier, 11.2, 7.8, 0.08, 0.12, 0.2, "T2")), TuplesKt.to((Object)"gwen", (Object)new CnChampionStat(53.6 * multiplier, 10.8, 26.4, 0.52, 0.3, 1.3, "T0")), TuplesKt.to((Object)"hecarim", (Object)new CnChampionStat(52.8 * multiplier, 11.5, 16.2, 0.35, 0.2, 0.75, "T1")), TuplesKt.to((Object)"irelia", (Object)new CnChampionStat(52.1 * multiplier, 10.5, 20.8, -0.15, 0.1, 0.8, "T1")), TuplesKt.to((Object)"janna", (Object)new CnChampionStat(53.1 * multiplier, 9.2, 6.5, 0.4, 0.15, 0.25, "T1")), TuplesKt.to((Object)"jarvan_iv", (Object)new CnChampionStat(52.0 * multiplier, 8.4, 3.9, 0.12, 0.04, 0.05, "T2")), TuplesKt.to((Object)"jax", (Object)new CnChampionStat(52.6 * multiplier, 11.0, 10.8, 0.22, 0.18, 0.4, "T1")), TuplesKt.to((Object)"jayce", (Object)new CnChampionStat(51.8 * multiplier, 8.6, 7.2, 0.05, -0.08, 0.15, "T2")), TuplesKt.to((Object)"jhin", (Object)new CnChampionStat(52.2 * multiplier, 17.5, 9.1, 0.18, 0.35, 0.2, "T1")), TuplesKt.to((Object)"jinx", (Object)new CnChampionStat(51.8 * multiplier, 16.8, 8.2, 0.14, 0.25, 0.15, "T1")), TuplesKt.to((Object)"kaisa", (Object)new CnChampionStat(52.7 * multiplier, 21.4, 18.6, 0.32, 0.6, 0.85, "T0")), TuplesKt.to((Object)"kalista", (Object)new CnChampionStat(53.1 * multiplier, 9.8, 31.2, 0.48, 0.25, 1.4, "T0")), TuplesKt.to((Object)"karma", (Object)new CnChampionStat(52.8 * multiplier, 13.5, 12.8, 0.28, 0.2, 0.5, "T1")), TuplesKt.to((Object)"kassadin", (Object)new CnChampionStat(53.3 * multiplier, 7.9, 22.4, 0.42, 0.15, 1.05, "T0")), TuplesKt.to((Object)"katarina", (Object)new CnChampionStat(51.9 * multiplier, 9.2, 17.6, -0.2, 0.08, 0.65, "T1")), TuplesKt.to((Object)"kayle", (Object)new CnChampionStat(52.4 * multiplier, 6.5, 8.9, 0.3, 0.12, 0.35, "T2")), TuplesKt.to((Object)"kayn", (Object)new CnChampionStat(52.6 * multiplier, 15.2, 23.4, 0.25, 0.4, 0.9, "T1")), TuplesKt.to((Object)"kennen", (Object)new CnChampionStat(52.0 * multiplier, 6.2, 4.5, 0.1, -0.04, 0.1, "T2")), TuplesKt.to((Object)"khazix", (Object)new CnChampionStat(53.0 * multiplier, 14.8, 25.6, 0.36, 0.3, 1.2, "T0")), TuplesKt.to((Object)"kindred", (Object)new CnChampionStat(52.9 * multiplier, 10.4, 19.8, 0.4, 0.18, 0.85, "T1")), TuplesKt.to((Object)"lee_sin", (Object)new CnChampionStat(52.5 * multiplier, 18.6, 27.4, 0.2, 0.45, 1.1, "T0")), TuplesKt.to((Object)"leona", (Object)new CnChampionStat(52.4 * multiplier, 12.8, 8.5, 0.18, 0.15, 0.3, "T1")), TuplesKt.to((Object)"lillia", (Object)new CnChampionStat(53.2 * multiplier, 8.7, 15.4, 0.45, 0.22, 0.7, "T1")), TuplesKt.to((Object)"lissandra", (Object)new CnChampionStat(52.7 * multiplier, 8.2, 11.5, 0.32, 0.1, 0.45, "T1")), TuplesKt.to((Object)"lucian", (Object)new CnChampionStat(51.9 * multiplier, 16.4, 14.2, 0.12, 0.28, 0.55, "T1")), TuplesKt.to((Object)"lulu", (Object)new CnChampionStat(53.4 * multiplier, 14.2, 21.5, 0.44, 0.35, 0.95, "T0")), TuplesKt.to((Object)"lux", (Object)new CnChampionStat(52.1 * multiplier, 19.8, 15.4, 0.1, 0.4, 0.5, "T1")), TuplesKt.to((Object)"malphite", (Object)new CnChampionStat(51.5 * multiplier, 11.2, 10.8, -0.15, -0.05, 0.2, "T2")), TuplesKt.to((Object)"maokai", (Object)new CnChampionStat(53.5 * multiplier, 10.6, 24.8, 0.5, 0.25, 1.15, "T0")), TuplesKt.to((Object)"master_yi", (Object)new CnChampionStat(50.4 * multiplier, 12.5, 16.8, -0.35, 0.1, 0.4, "T2")), TuplesKt.to((Object)"milio", (Object)new CnChampionStat(53.6 * multiplier, 11.8, 22.0, 0.55, 0.3, 1.0, "T0")), TuplesKt.to((Object)"miss_fortune", (Object)new CnChampionStat(51.7 * multiplier, 15.4, 5.8, 0.08, 0.15, 0.1, "T2")), TuplesKt.to((Object)"mordekaiser", (Object)new CnChampionStat(53.3 * multiplier, 13.9, 26.2, 0.46, 0.35, 1.25, "T0")), TuplesKt.to((Object)"morgana", (Object)new CnChampionStat(51.8 * multiplier, 10.5, 13.2, 0.14, 0.05, 0.4, "T2")), TuplesKt.to((Object)"nami", (Object)new CnChampionStat(52.9 * multiplier, 12.1, 7.4, 0.3, 0.18, 0.2, "T1")), TuplesKt.to((Object)"nasus", (Object)new CnChampionStat(50.6 * multiplier, 8.4, 4.2, -0.2, -0.1, -0.05, "T3")), TuplesKt.to((Object)"nautilus", (Object)new CnChampionStat(52.6 * multiplier, 13.4, 11.8, 0.24, 0.2, 0.45, "T1")), TuplesKt.to((Object)"nilah", (Object)new CnChampionStat(53.7 * multiplier, 7.2, 16.5, 0.58, 0.15, 0.8, "T0")), TuplesKt.to((Object)"nunu", (Object)new CnChampionStat(52.3 * multiplier, 6.8, 3.2, 0.2, 0.05, 0.0, "T2")), TuplesKt.to((Object)"olaf", (Object)new CnChampionStat(52.7 * multiplier, 8.5, 9.8, 0.32, 0.12, 0.4, "T1")), TuplesKt.to((Object)"orianna", (Object)new CnChampionStat(52.4 * multiplier, 9.8, 4.5, 0.22, 0.1, 0.15, "T1")), TuplesKt.to((Object)"ornn", (Object)new CnChampionStat(53.0 * multiplier, 9.2, 12.6, 0.38, 0.16, 0.55, "T1")), TuplesKt.to((Object)"pantheon", (Object)new CnChampionStat(52.1 * multiplier, 8.9, 6.4, 0.15, 0.08, 0.2, "T2")), TuplesKt.to((Object)"pyke", (Object)new CnChampionStat(52.3 * multiplier, 11.5, 23.8, 0.2, 0.15, 1.0, "T1")), TuplesKt.to((Object)"rakan", (Object)new CnChampionStat(52.8 * multiplier, 9.4, 5.8, 0.3, 0.12, 0.2, "T1")), TuplesKt.to((Object)"rammus", (Object)new CnChampionStat(52.5 * multiplier, 6.9, 7.8, 0.25, 0.08, 0.3, "T2")), TuplesKt.to((Object)"renekton", (Object)new CnChampionStat(52.2 * multiplier, 10.4, 8.2, 0.18, 0.12, 0.25, "T1")), TuplesKt.to((Object)"rengar", (Object)new CnChampionStat(52.6 * multiplier, 7.8, 14.5, 0.3, 0.1, 0.65, "T1")), TuplesKt.to((Object)"riven", (Object)new CnChampionStat(53.1 * multiplier, 8.6, 12.4, 0.4, 0.14, 0.5, "T1")), TuplesKt.to((Object)"samira", (Object)new CnChampionStat(52.4 * multiplier, 12.8, 28.6, 0.22, 0.2, 1.35, "T0")), TuplesKt.to((Object)"senna", (Object)new CnChampionStat(52.2 * multiplier, 9.8, 7.6, 0.18, 0.1, 0.25, "T1")), TuplesKt.to((Object)"seraphine", (Object)new CnChampionStat(52.0 * multiplier, 11.2, 4.8, 0.12, 0.05, 0.1, "T2")), TuplesKt.to((Object)"sett", (Object)new CnChampionStat(52.8 * multiplier, 15.6, 21.4, 0.34, 0.3, 0.9, "T0")), TuplesKt.to((Object)"shen", (Object)new CnChampionStat(52.9 * multiplier, 7.5, 6.8, 0.35, 0.1, 0.25, "T1")), TuplesKt.to((Object)"shyvana", (Object)new CnChampionStat(51.8 * multiplier, 7.9, 4.5, 0.05, -0.05, 0.1, "T2")), TuplesKt.to((Object)"singed", (Object)new CnChampionStat(53.2 * multiplier, 4.5, 3.2, 0.45, 0.05, 0.1, "T1")), TuplesKt.to((Object)"sion", (Object)new CnChampionStat(52.6 * multiplier, 8.8, 8.4, 0.28, 0.1, 0.35, "T1")), TuplesKt.to((Object)"sivir", (Object)new CnChampionStat(52.1 * multiplier, 12.4, 6.2, 0.15, 0.18, 0.15, "T2")), TuplesKt.to((Object)"sona", (Object)new CnChampionStat(53.0 * multiplier, 8.6, 3.8, 0.38, 0.1, 0.1, "T1")), TuplesKt.to((Object)"soraka", (Object)new CnChampionStat(52.8 * multiplier, 12.5, 18.9, 0.3, 0.22, 0.85, "T1")), TuplesKt.to((Object)"swain", (Object)new CnChampionStat(53.4 * multiplier, 8.9, 14.8, 0.48, 0.18, 0.65, "T0")), TuplesKt.to((Object)"syndra", (Object)new CnChampionStat(53.5 * multiplier, 12.4, 25.2, 0.5, 0.28, 1.2, "T0")), TuplesKt.to((Object)"talon", (Object)new CnChampionStat(53.0 * multiplier, 11.2, 22.8, 0.38, 0.22, 1.05, "T0")), TuplesKt.to((Object)"teemo", (Object)new CnChampionStat(49.8 * multiplier, 8.2, 8.9, -0.4, -0.15, 0.2, "T3")), TuplesKt.to((Object)"thresh", (Object)new CnChampionStat(52.7 * multiplier, 16.5, 15.8, 0.28, 0.35, 0.6, "T1")), TuplesKt.to((Object)"tristana", (Object)new CnChampionStat(52.3 * multiplier, 14.8, 11.2, 0.2, 0.22, 0.45, "T1")), TuplesKt.to((Object)"tryndamere", (Object)new CnChampionStat(51.6 * multiplier, 7.8, 9.4, 0.08, 0.05, 0.3, "T2")), TuplesKt.to((Object)"twisted_fate", (Object)new CnChampionStat(52.6 * multiplier, 8.4, 5.6, 0.32, 0.12, 0.2, "T1")), TuplesKt.to((Object)"twitch", (Object)new CnChampionStat(53.1 * multiplier, 9.8, 20.4, 0.42, 0.2, 0.95, "T0")), TuplesKt.to((Object)"urgot", (Object)new CnChampionStat(52.8 * multiplier, 8.6, 7.4, 0.3, 0.1, 0.25, "T1")), TuplesKt.to((Object)"varus", (Object)new CnChampionStat(52.4 * multiplier, 13.6, 9.8, 0.22, 0.18, 0.35, "T1")), TuplesKt.to((Object)"vayne", (Object)new CnChampionStat(52.2 * multiplier, 14.5, 17.8, 0.18, 0.25, 0.75, "T1")), TuplesKt.to((Object)"veigar", (Object)new CnChampionStat(51.9 * multiplier, 9.8, 6.5, 0.12, 0.08, 0.2, "T2")), TuplesKt.to((Object)"vex", (Object)new CnChampionStat(53.0 * multiplier, 10.4, 14.2, 0.36, 0.18, 0.6, "T1")), TuplesKt.to((Object)"vi", (Object)new CnChampionStat(52.3 * multiplier, 11.0, 5.2, 0.2, 0.12, 0.15, "T1")), TuplesKt.to((Object)"viego", (Object)new CnChampionStat(53.2 * multiplier, 16.8, 29.4, 0.45, 0.4, 1.45, "T0")), TuplesKt.to((Object)"viktor", (Object)new CnChampionStat(53.1 * multiplier, 9.5, 12.0, 0.4, 0.15, 0.5, "T1")), TuplesKt.to((Object)"vladimir", (Object)new CnChampionStat(53.3 * multiplier, 10.2, 23.5, 0.44, 0.2, 1.1, "T0")), TuplesKt.to((Object)"volibear", (Object)new CnChampionStat(52.9 * multiplier, 12.4, 15.6, 0.35, 0.25, 0.7, "T1")), TuplesKt.to((Object)"warwick", (Object)new CnChampionStat(52.1 * multiplier, 8.2, 4.8, 0.14, 0.06, 0.15, "T2")), TuplesKt.to((Object)"wukong", (Object)new CnChampionStat(52.7 * multiplier, 9.4, 7.2, 0.28, 0.14, 0.3, "T1")), TuplesKt.to((Object)"xayah", (Object)new CnChampionStat(52.0 * multiplier, 11.8, 6.4, 0.15, 0.1, 0.2, "T1")), TuplesKt.to((Object)"xin_zhao", (Object)new CnChampionStat(52.2 * multiplier, 8.9, 4.5, 0.18, 0.08, 0.15, "T2")), TuplesKt.to((Object)"yasuo", (Object)new CnChampionStat(51.4 * multiplier, 19.5, 24.2, -0.1, 0.35, 1.0, "T1")), TuplesKt.to((Object)"yone", (Object)new CnChampionStat(52.6 * multiplier, 18.2, 27.8, 0.3, 0.35, 1.3, "T0")), TuplesKt.to((Object)"yuumi", (Object)new CnChampionStat(52.4 * multiplier, 13.8, 38.5, 0.22, 0.25, 1.8, "T0")), TuplesKt.to((Object)"zed", (Object)new CnChampionStat(52.1 * multiplier, 14.6, 28.4, 0.15, 0.2, 1.3, "T0")), TuplesKt.to((Object)"zeri", (Object)new CnChampionStat(53.2 * multiplier, 11.4, 21.0, 0.45, 0.25, 0.95, "T0")), TuplesKt.to((Object)"ziggs", (Object)new CnChampionStat(52.2 * multiplier, 6.8, 3.4, 0.18, 0.06, 0.1, "T2")), TuplesKt.to((Object)"zoe", (Object)new CnChampionStat(52.7 * multiplier, 8.1, 12.6, 0.3, 0.12, 0.55, "T1")), TuplesKt.to((Object)"zyra", (Object)new CnChampionStat(53.4 * multiplier, 11.2, 19.5, 0.48, 0.22, 0.9, "T0"))};
        return MapsKt.mapOf((Pair[])pairArray);
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public final Object getFilteredRankings(@NotNull Context context, @NotNull TencentRankTier targetTier, @Nullable LaneRole lane, @NotNull Continuation<? super List<Champion>> $completion) {
        if (!($completion instanceof getFilteredRankings.1)) ** GOTO lbl-1000
        var16_5 = $completion;
        if ((var16_5.label & -2147483648) != 0) {
            var16_5.label -= -2147483648;
        } else lbl-1000:
        // 2 sources

        {
            $continuation = new ContinuationImpl(this, $completion){
                Object L$0;
                Object L$1;
                Object L$2;
                /* synthetic */ Object result;
                final /* synthetic */ ChineseMetaSyncService this$0;
                int label;
                {
                    this.this$0 = this$0;
                    super($completion);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.getFilteredRankings(null, null, null, (Continuation<? super List<Champion>>)((Continuation)this));
                }
            };
        }
        $result = $continuation.result;
        var17_7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch ($continuation.label) {
            case 0: {
                ResultKt.throwOnFailure((Object)$result);
                $continuation.L$0 = SpillingKt.nullOutSpilledVariable((Object)context);
                $continuation.L$1 = SpillingKt.nullOutSpilledVariable((Object)targetTier);
                $continuation.L$2 = lane;
                $continuation.label = 1;
                v0 = ChineseMetaSyncService.syncChineseMeta$default(this, context, targetTier, false, (Continuation)$continuation, 4, null);
                if (v0 == var17_7) {
                    return var17_7;
                }
                ** GOTO lbl26
            }
            case 1: {
                lane = (LaneRole)$continuation.L$2;
                targetTier = (TencentRankTier)$continuation.L$1;
                context = (Context)$continuation.L$0;
                ResultKt.throwOnFailure((Object)$result);
                v0 = $result;
lbl26:
                // 2 sources

                list = WildRiftRepository.INSTANCE.getChampions().toList();
                if (lane != null) {
                    $this$filter\1 = list;
                    $i$f$filter\1\550 = false;
                    var8_11 = $this$filter\1;
                    destination\2 = new ArrayList<E>();
                    $i$f$filterTo\2\557 = false;
                    for (T element\2 : $this$filterTo\2) {
                        it\3 = (Champion)element\2;
                        $i$a$-filter-ChineseMetaSyncService$getFilteredRankings$2\3\558\0 = false;
                        if (!(it\3.getPrimaryRole() == lane || it\3.getSecondaryRoles().contains((Object)lane) != false)) continue;
                        destination\2.add(element\2);
                    }
                    list = (List)destination\2;
                }
                $this$sortedByDescending\4 = list;
                $i$f$sortedByDescending\4\553 = false;
                return CollectionsKt.sortedWith((Iterable)$this$sortedByDescending\4, (Comparator)new Comparator(){

                    /*
                     * WARNING - void declaration
                     */
                    public final int compare(T a, T b) {
                        void it\2;
                        Champion champion = (Champion)b;
                        boolean bl = false;
                        Comparable comparable = Double.valueOf(champion.getWinrate());
                        champion = (Champion)a;
                        Comparable comparable2 = comparable;
                        boolean bl2 = false;
                        return ComparisonsKt.compareValues((Comparable)comparable2, (Comparable)Double.valueOf(it\2.getWinrate()));
                    }
                });
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public static /* synthetic */ Object getFilteredRankings$default(ChineseMetaSyncService chineseMetaSyncService, Context context, TencentRankTier tencentRankTier, LaneRole laneRole, Continuation continuation, int n, Object object) {
        if ((n & 2) != 0) {
            tencentRankTier = TencentRankTier.DIAMOND_PLUS;
        }
        if ((n & 4) != 0) {
            laneRole = null;
        }
        return chineseMetaSyncService.getFilteredRankings(context, tencentRankTier, laneRole, (Continuation<? super List<Champion>>)continuation);
    }

    public static final /* synthetic */ MutableStateFlow access$get_syncState$p() {
        return _syncState;
    }

    public static final /* synthetic */ MutableStateFlow access$get_currentTier$p() {
        return _currentTier;
    }

    public static final /* synthetic */ Pair access$fetchAndParseTencentLiveStats(ChineseMetaSyncService $this, TencentRankTier targetTier) {
        return $this.fetchAndParseTencentLiveStats(targetTier);
    }

    public static final /* synthetic */ Map access$generateChineseStatsSnapshot(ChineseMetaSyncService $this, TencentRankTier tier) {
        return $this.generateChineseStatsSnapshot(tier);
    }

    public static final /* synthetic */ double access$roundTwoDecimals(ChineseMetaSyncService $this, double value) {
        return $this.roundTwoDecimals(value);
    }

    @Metadata(mv={2, 2, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\nH\u00c6\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006$"}, d2={"Lcom/example/data/sync/ChineseMetaSyncService$CnChampionStat;", "", "winRate", "", "pickRate", "banRate", "defaultWinDelta", "defaultPickDelta", "defaultBanDelta", "cnTier", "", "<init>", "(DDDDDDLjava/lang/String;)V", "getWinRate", "()D", "getPickRate", "getBanRate", "getDefaultWinDelta", "getDefaultPickDelta", "getDefaultBanDelta", "getCnTier", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app"})
    @StabilityInferred(parameters=1)
    public static final class CnChampionStat {
        private final double winRate;
        private final double pickRate;
        private final double banRate;
        private final double defaultWinDelta;
        private final double defaultPickDelta;
        private final double defaultBanDelta;
        @NotNull
        private final String cnTier;
        public static final int $stable;

        public CnChampionStat(double winRate, double pickRate, double banRate, double defaultWinDelta, double defaultPickDelta, double defaultBanDelta, @NotNull String cnTier) {
            Intrinsics.checkNotNullParameter((Object)cnTier, (String)"cnTier");
            this.winRate = winRate;
            this.pickRate = pickRate;
            this.banRate = banRate;
            this.defaultWinDelta = defaultWinDelta;
            this.defaultPickDelta = defaultPickDelta;
            this.defaultBanDelta = defaultBanDelta;
            this.cnTier = cnTier;
        }

        public final double getWinRate() {
            return this.winRate;
        }

        public final double getPickRate() {
            return this.pickRate;
        }

        public final double getBanRate() {
            return this.banRate;
        }

        public final double getDefaultWinDelta() {
            return this.defaultWinDelta;
        }

        public final double getDefaultPickDelta() {
            return this.defaultPickDelta;
        }

        public final double getDefaultBanDelta() {
            return this.defaultBanDelta;
        }

        @NotNull
        public final String getCnTier() {
            return this.cnTier;
        }

        public final double component1() {
            return this.winRate;
        }

        public final double component2() {
            return this.pickRate;
        }

        public final double component3() {
            return this.banRate;
        }

        public final double component4() {
            return this.defaultWinDelta;
        }

        public final double component5() {
            return this.defaultPickDelta;
        }

        public final double component6() {
            return this.defaultBanDelta;
        }

        @NotNull
        public final String component7() {
            return this.cnTier;
        }

        @NotNull
        public final CnChampionStat copy(double winRate, double pickRate, double banRate, double defaultWinDelta, double defaultPickDelta, double defaultBanDelta, @NotNull String cnTier) {
            Intrinsics.checkNotNullParameter((Object)cnTier, (String)"cnTier");
            return new CnChampionStat(winRate, pickRate, banRate, defaultWinDelta, defaultPickDelta, defaultBanDelta, cnTier);
        }

        public static /* synthetic */ CnChampionStat copy$default(CnChampionStat cnChampionStat, double d, double d2, double d3, double d4, double d5, double d6, String string, int n, Object object) {
            if ((n & 1) != 0) {
                d = cnChampionStat.winRate;
            }
            if ((n & 2) != 0) {
                d2 = cnChampionStat.pickRate;
            }
            if ((n & 4) != 0) {
                d3 = cnChampionStat.banRate;
            }
            if ((n & 8) != 0) {
                d4 = cnChampionStat.defaultWinDelta;
            }
            if ((n & 0x10) != 0) {
                d5 = cnChampionStat.defaultPickDelta;
            }
            if ((n & 0x20) != 0) {
                d6 = cnChampionStat.defaultBanDelta;
            }
            if ((n & 0x40) != 0) {
                string = cnChampionStat.cnTier;
            }
            return cnChampionStat.copy(d, d2, d3, d4, d5, d6, string);
        }

        @NotNull
        public String toString() {
            return "CnChampionStat(winRate=" + this.winRate + ", pickRate=" + this.pickRate + ", banRate=" + this.banRate + ", defaultWinDelta=" + this.defaultWinDelta + ", defaultPickDelta=" + this.defaultPickDelta + ", defaultBanDelta=" + this.defaultBanDelta + ", cnTier=" + this.cnTier + ")";
        }

        public int hashCode() {
            int result = Double.hashCode(this.winRate);
            result = result * 31 + Double.hashCode(this.pickRate);
            result = result * 31 + Double.hashCode(this.banRate);
            result = result * 31 + Double.hashCode(this.defaultWinDelta);
            result = result * 31 + Double.hashCode(this.defaultPickDelta);
            result = result * 31 + Double.hashCode(this.defaultBanDelta);
            result = result * 31 + this.cnTier.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CnChampionStat)) {
                return false;
            }
            CnChampionStat cnChampionStat = (CnChampionStat)other;
            if (Double.compare(this.winRate, cnChampionStat.winRate) != 0) {
                return false;
            }
            if (Double.compare(this.pickRate, cnChampionStat.pickRate) != 0) {
                return false;
            }
            if (Double.compare(this.banRate, cnChampionStat.banRate) != 0) {
                return false;
            }
            if (Double.compare(this.defaultWinDelta, cnChampionStat.defaultWinDelta) != 0) {
                return false;
            }
            if (Double.compare(this.defaultPickDelta, cnChampionStat.defaultPickDelta) != 0) {
                return false;
            }
            if (Double.compare(this.defaultBanDelta, cnChampionStat.defaultBanDelta) != 0) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.cnTier, (Object)cnChampionStat.cnTier);
        }
    }

    @Metadata(mv={2, 2, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[TencentRankTier.values().length];
            try {
                nArray[TencentRankTier.CHALLENGER.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[TencentRankTier.MASTER_PLUS.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[TencentRankTier.DIAMOND_PLUS.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[TencentRankTier.ALL_RANKS.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}
