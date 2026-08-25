package com.example.ui.components;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FlowLayoutKt;
import androidx.compose.foundation.layout.FlowRowOverflow;
import androidx.compose.foundation.layout.FlowRowScope;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AutoAwesomeKt;
import androidx.compose.material.icons.filled.CameraAltKt;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.NavigateNextKt;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardElevation;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.SwitchDefaults;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TabKt;
import androidx.compose.material3.TabPosition;
import androidx.compose.material3.TabRowDefaults;
import androidx.compose.material3.TabRowKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.R;
import com.example.data.WildRiftRepository;
import com.example.data.WildRiftSpellsAndRunes;
import com.example.model.Champion;
import com.example.model.DraftAnalysisResult;
import com.example.model.DraftRecommendation;
import com.example.model.ItemCategory;
import com.example.model.LaneRole;
import com.example.model.MapObjectiveItem;
import com.example.model.RuneItem;
import com.example.model.WildRiftItem;
import com.example.ui.components.ComposableSingletons;
import com.example.ui.theme.ColorKt;
import com.example.util.TranslatorKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FloatingAssistantOverlay.kt */
@Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"��X\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001ao\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00072\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\u0016\u001a\r\u0010\u0017\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0018\u001aI\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0014\u0010\u001f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0004\u0012\u00020\u00010\u000eH\u0003¢\u0006\u0002\u0010 \u001aU\u0010!\u001a\u00020\u00012\b\u0010\"\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010%\u001aU\u0010&\u001a\u00020\u00012\b\u0010\"\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010%¨\u0006'²\u0006\n\u0010(\u001a\u00020)X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020)X\u008a\u008e\u0002²\u0006\n\u0010+\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010,\u001a\u00020-X\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\f\u0010\"\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020\u001bX\u008a\u008e\u0002²\u0006\f\u0010/\u001a\u0004\u0018\u00010\u001eX\u008a\u008e\u0002²\u0006\n\u00100\u001a\u00020\u001bX\u008a\u008e\u0002"}, d2 = {"FloatingAssistantOverlay", "", "isVisible", "", "onDismiss", "Lkotlin/Function0;", "initialRole", "Lcom/example/model/LaneRole;", "modifier", "Landroidx/compose/ui/Modifier;", "(ZLkotlin/jvm/functions/Function0;Lcom/example/model/LaneRole;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "OverlayDraftTabContent", "activeRole", "onRoleChange", "Lkotlin/Function1;", "isFirstPick", "onFirstPickToggle", "analysis", "Lcom/example/model/DraftAnalysisResult;", "onLockChampion", "Lcom/example/model/Champion;", "onSimulateScan", "(Lcom/example/model/LaneRole;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Lcom/example/model/DraftAnalysisResult;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "OverlayObjectivesTabContent", "(Landroidx/compose/runtime/Composer;I)V", "OverlayItemsTabContent", "searchQuery", "", "onSearchChange", "selectedCategory", "Lcom/example/model/ItemCategory;", "onCategoryChange", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/example/model/ItemCategory;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "OverlayRunesTabContent", "lockedChampion", "onSelectChampion", "onClearChampion", "(Lcom/example/model/Champion;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "OverlaySpellsTabContent", "app", "dragOffsetY", "", "dragOffsetX", "showSpeechBubble", "selectedTab", "Lcom/example/ui/components/OverlayTab;", "itemSearchQuery", "itemSelectedCategory", "runeSearchQuery"})
@SourceDebugExtension({"SMAP\nFloatingAssistantOverlay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatingAssistantOverlay.kt\ncom/example/ui/components/FloatingAssistantOverlayKt\n+ 2 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 3 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Column.kt\nandroidx/compose/foundation/layout/ColumnKt\n+ 6 Layout.kt\nandroidx/compose/ui/layout/LayoutKt\n+ 7 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 8 Composer.kt\nandroidx/compose/runtime/Updater\n+ 9 Dp.kt\nandroidx/compose/ui/unit/DpKt\n+ 10 Row.kt\nandroidx/compose/foundation/layout/RowKt\n+ 11 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 12 Box.kt\nandroidx/compose/foundation/layout/BoxKt\n+ 13 SnapshotFloatState.kt\nandroidx/compose/runtime/PrimitiveSnapshotStateKt__SnapshotFloatStateKt\n+ 14 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n+ 15 LazyDsl.kt\nandroidx/compose/foundation/lazy/LazyDslKt\n*L\n1#1,1308:1\n77#2:1309\n1225#3,6:1310\n1225#3,6:1316\n1225#3,6:1322\n1225#3,6:1328\n1225#3,6:1334\n1225#3,6:1340\n1225#3,3:1346\n1228#3,3:1350\n1225#3,6:1353\n1225#3,6:1359\n1225#3,6:1365\n1225#3,6:1371\n1225#3,6:1377\n1225#3,6:1383\n1225#3,6:1504\n1225#3,6:1596\n1225#3,6:1655\n1225#3,6:1830\n1225#3,3:1836\n1228#3,3:1842\n1225#3,6:1886\n1225#3,3:1896\n1228#3,3:1902\n1225#3,6:1987\n1225#3,3:2164\n1228#3,3:2170\n1225#3,6:2255\n1225#3,6:2464\n1225#3,6:2508\n1225#3,6:2708\n1225#3,6:2762\n1225#3,6:2768\n1225#3,6:2774\n1225#3,6:2780\n1225#3,6:2786\n1225#3,6:2792\n1225#3,6:2798\n1225#3,6:2804\n1225#3,6:2810\n1225#3,6:2816\n1225#3,6:2822\n1225#3,6:2828\n1225#3,6:2871\n1225#3,6:2878\n1225#3,6:2898\n1225#3,6:2904\n1225#3,6:2911\n1225#3,6:2985\n1225#3,6:2991\n1225#3,6:3000\n1225#3,6:3006\n1225#3,6:3259\n1225#3,6:3322\n1#4:1349\n85#5:1389\n82#5,6:1390\n88#5:1424\n85#5:1733\n81#5,7:1734\n88#5:1769\n92#5:1773\n92#5:1828\n85#5:1845\n82#5,6:1846\n88#5:1880\n92#5:1895\n85#5:1905\n82#5,6:1906\n88#5:1940\n92#5:1996\n85#5:1997\n82#5,6:1998\n88#5:2032\n85#5:2107\n81#5,7:2108\n88#5:2143\n92#5:2147\n92#5:2163\n85#5:2173\n82#5,6:2174\n88#5:2208\n92#5:2264\n85#5:2265\n82#5,6:2266\n88#5:2300\n85#5:2375\n81#5,7:2376\n88#5:2411\n92#5:2415\n92#5:2431\n85#5:2472\n82#5,6:2473\n88#5:2507\n85#5:2626\n81#5,7:2627\n88#5:2662\n92#5:2666\n92#5:2892\n85#5,3:2953\n88#5:2984\n92#5:3055\n85#5:3061\n82#5,6:3062\n88#5:3096\n85#5:3172\n82#5,6:3173\n88#5:3207\n92#5:3250\n92#5:3307\n85#5:3442\n82#5,6:3443\n88#5:3477\n92#5:3493\n85#5:3507\n82#5,6:3508\n88#5:3542\n92#5:3638\n78#6,6:1396\n85#6,4:1411\n89#6,2:1421\n78#6,6:1430\n85#6,4:1445\n89#6,2:1455\n78#6,6:1467\n85#6,4:1482\n89#6,2:1492\n93#6:1500\n78#6,6:1518\n85#6,4:1533\n89#6,2:1543\n93#6:1551\n93#6:1555\n78#6,6:1565\n85#6,4:1580\n89#6,2:1590\n78#6,6:1606\n85#6,4:1621\n89#6,2:1631\n93#6:1639\n93#6:1644\n78#6,6:1665\n85#6,4:1680\n89#6,2:1690\n78#6,6:1702\n85#6,4:1717\n89#6,2:1727\n78#6,6:1741\n85#6,4:1756\n89#6,2:1766\n93#6:1772\n93#6:1776\n78#6,6:1786\n85#6,4:1801\n89#6,2:1811\n93#6:1818\n93#6:1822\n93#6:1827\n78#6,6:1852\n85#6,4:1867\n89#6,2:1877\n93#6:1894\n78#6,6:1912\n85#6,4:1927\n89#6,2:1937\n78#6,6:1950\n85#6,4:1965\n89#6,2:1975\n93#6:1981\n93#6:1995\n78#6,6:2004\n85#6,4:2019\n89#6,2:2029\n78#6,6:2039\n85#6,4:2054\n89#6,2:2064\n78#6,6:2076\n85#6,4:2091\n89#6,2:2101\n78#6,6:2115\n85#6,4:2130\n89#6,2:2140\n93#6:2146\n93#6:2150\n93#6:2155\n93#6:2162\n78#6,6:2180\n85#6,4:2195\n89#6,2:2205\n78#6,6:2218\n85#6,4:2233\n89#6,2:2243\n93#6:2249\n93#6:2263\n78#6,6:2272\n85#6,4:2287\n89#6,2:2297\n78#6,6:2307\n85#6,4:2322\n89#6,2:2332\n78#6,6:2344\n85#6,4:2359\n89#6,2:2369\n78#6,6:2383\n85#6,4:2398\n89#6,2:2408\n93#6:2414\n93#6:2418\n93#6:2423\n93#6:2430\n78#6,6:2479\n85#6,4:2494\n89#6,2:2504\n78#6,6:2517\n85#6,4:2532\n89#6,2:2542\n78#6,6:2555\n85#6,4:2570\n89#6,2:2580\n78#6,6:2592\n85#6,4:2607\n89#6,2:2617\n93#6:2624\n78#6,6:2634\n85#6,4:2649\n89#6,2:2659\n93#6:2665\n93#6:2669\n78#6,6:2679\n85#6,4:2694\n89#6,2:2704\n93#6:2717\n93#6:2721\n78#6,6:2733\n85#6,4:2748\n89#6,2:2758\n93#6:2836\n78#6,6:2842\n85#6,4:2857\n89#6,2:2867\n93#6:2887\n93#6:2891\n78#6,6:2923\n85#6,4:2938\n89#6,2:2948\n78#6,6:2956\n85#6,4:2971\n89#6,2:2981\n78#6,6:3018\n85#6,4:3033\n89#6,2:3043\n93#6:3050\n93#6:3054\n93#6:3058\n78#6,6:3068\n85#6,4:3083\n89#6,2:3093\n78#6,6:3100\n85#6,4:3115\n89#6,2:3125\n93#6:3131\n78#6,6:3141\n85#6,4:3156\n89#6,2:3166\n78#6,6:3179\n85#6,4:3194\n89#6,2:3204\n78#6,6:3214\n85#6,4:3229\n89#6,2:3239\n93#6:3245\n93#6:3249\n93#6:3253\n78#6,6:3269\n85#6,4:3284\n89#6,2:3294\n93#6:3302\n93#6:3306\n78#6,6:3336\n85#6,4:3351\n89#6,2:3361\n93#6:3367\n78#6,6:3403\n85#6,4:3418\n89#6,2:3428\n93#6:3438\n78#6,6:3449\n85#6,4:3464\n89#6,2:3474\n93#6:3492\n78#6,6:3514\n85#6,4:3529\n89#6,2:3539\n78#6,6:3553\n85#6,4:3568\n89#6,2:3578\n78#6,6:3593\n85#6,4:3608\n89#6,2:3618\n93#6:3624\n93#6:3629\n93#6:3637\n368#7,9:1402\n377#7:1423\n368#7,9:1436\n377#7:1457\n368#7,9:1473\n377#7:1494\n378#7,2:1498\n368#7,9:1524\n377#7:1545\n378#7,2:1549\n378#7,2:1553\n368#7,9:1571\n377#7:1592\n368#7,9:1612\n377#7:1633\n378#7,2:1637\n378#7,2:1642\n368#7,9:1671\n377#7:1692\n368#7,9:1708\n377#7:1729\n368#7,9:1747\n377#7:1768\n378#7,2:1770\n378#7,2:1774\n368#7,9:1792\n377#7:1813\n378#7,2:1816\n378#7,2:1820\n378#7,2:1825\n368#7,9:1858\n377#7:1879\n378#7,2:1892\n368#7,9:1918\n377#7:1939\n368#7,9:1956\n377#7:1977\n378#7,2:1979\n378#7,2:1993\n368#7,9:2010\n377#7:2031\n368#7,9:2045\n377#7:2066\n368#7,9:2082\n377#7:2103\n368#7,9:2121\n377#7:2142\n378#7,2:2144\n378#7,2:2148\n378#7,2:2153\n378#7,2:2160\n368#7,9:2186\n377#7:2207\n368#7,9:2224\n377#7:2245\n378#7,2:2247\n378#7,2:2261\n368#7,9:2278\n377#7:2299\n368#7,9:2313\n377#7:2334\n368#7,9:2350\n377#7:2371\n368#7,9:2389\n377#7:2410\n378#7,2:2412\n378#7,2:2416\n378#7,2:2421\n378#7,2:2428\n368#7,9:2485\n377#7:2506\n368#7,9:2523\n377#7:2544\n368#7,9:2561\n377#7:2582\n368#7,9:2598\n377#7:2619\n378#7,2:2622\n368#7,9:2640\n377#7:2661\n378#7,2:2663\n378#7,2:2667\n368#7,9:2685\n377#7:2706\n378#7,2:2715\n378#7,2:2719\n368#7,9:2739\n377#7:2760\n378#7,2:2834\n368#7,9:2848\n377#7:2869\n378#7,2:2885\n378#7,2:2889\n368#7,9:2929\n377#7:2950\n368#7,9:2962\n377#7:2983\n368#7,9:3024\n377#7:3045\n378#7,2:3048\n378#7,2:3052\n378#7,2:3056\n368#7,9:3074\n377#7:3095\n368#7,9:3106\n377#7:3127\n378#7,2:3129\n368#7,9:3147\n377#7:3168\n368#7,9:3185\n377#7:3206\n368#7,9:3220\n377#7:3241\n378#7,2:3243\n378#7,2:3247\n378#7,2:3251\n368#7,9:3275\n377#7:3296\n378#7,2:3300\n378#7,2:3304\n368#7,9:3342\n377#7:3363\n378#7,2:3365\n368#7,9:3409\n377#7:3430\n378#7,2:3436\n368#7,9:3455\n377#7:3476\n378#7,2:3490\n368#7,9:3520\n377#7:3541\n368#7,9:3559\n377#7:3580\n368#7,9:3599\n377#7:3620\n378#7,2:3622\n378#7,2:3627\n378#7,2:3635\n4032#8,6:1415\n4032#8,6:1449\n4032#8,6:1486\n4032#8,6:1537\n4032#8,6:1584\n4032#8,6:1625\n4032#8,6:1684\n4032#8,6:1721\n4032#8,6:1760\n4032#8,6:1805\n4032#8,6:1871\n4032#8,6:1931\n4032#8,6:1969\n4032#8,6:2023\n4032#8,6:2058\n4032#8,6:2095\n4032#8,6:2134\n4032#8,6:2199\n4032#8,6:2237\n4032#8,6:2291\n4032#8,6:2326\n4032#8,6:2363\n4032#8,6:2402\n4032#8,6:2498\n4032#8,6:2536\n4032#8,6:2574\n4032#8,6:2611\n4032#8,6:2653\n4032#8,6:2698\n4032#8,6:2752\n4032#8,6:2861\n4032#8,6:2942\n4032#8,6:2975\n4032#8,6:3037\n4032#8,6:3087\n4032#8,6:3119\n4032#8,6:3160\n4032#8,6:3198\n4032#8,6:3233\n4032#8,6:3288\n4032#8,6:3355\n4032#8,6:3422\n4032#8,6:3468\n4032#8,6:3533\n4032#8,6:3572\n4032#8,6:3612\n148#9:1425\n148#9:1426\n148#9:1496\n148#9:1497\n148#9:1502\n148#9:1503\n148#9:1510\n148#9:1547\n148#9:1548\n148#9:1557\n148#9:1558\n148#9:1595\n148#9:1602\n148#9:1635\n148#9:1636\n148#9:1646\n158#9:1647\n148#9:1648\n148#9:1649\n148#9:1650\n148#9:1651\n148#9:1653\n148#9:1654\n148#9:1661\n148#9:1731\n148#9:1732\n148#9:1815\n148#9:1829\n148#9:1881\n148#9:1882\n148#9:1883\n148#9:1884\n148#9:1885\n148#9:1941\n148#9:1942\n148#9:1943\n148#9:1983\n148#9:1984\n148#9:1985\n148#9:1986\n148#9:2033\n148#9:2034\n148#9:2035\n148#9:2105\n148#9:2106\n148#9:2152\n148#9:2157\n148#9:2158\n148#9:2159\n148#9:2209\n148#9:2210\n148#9:2211\n148#9:2251\n148#9:2252\n148#9:2253\n148#9:2254\n148#9:2301\n148#9:2302\n148#9:2303\n148#9:2373\n148#9:2374\n148#9:2420\n148#9:2425\n148#9:2426\n148#9:2427\n158#9:2462\n148#9:2471\n148#9:2546\n148#9:2584\n148#9:2585\n148#9:2621\n148#9:2714\n148#9:2723\n148#9:2724\n148#9:2725\n148#9:2838\n148#9:2877\n148#9:2884\n148#9:2893\n148#9:2894\n158#9:2895\n148#9:2896\n148#9:2897\n148#9:2910\n148#9:2952\n148#9:2997\n148#9:2998\n158#9:2999\n148#9:3047\n148#9:3060\n148#9:3133\n148#9:3170\n148#9:3171\n148#9:3255\n148#9:3256\n148#9:3257\n148#9:3258\n148#9:3265\n148#9:3298\n148#9:3299\n148#9:3321\n148#9:3328\n148#9:3432\n148#9:3433\n148#9:3434\n148#9:3435\n148#9:3441\n148#9:3478\n148#9:3486\n148#9:3487\n148#9:3488\n148#9:3489\n148#9:3506\n148#9:3543\n148#9:3544\n148#9:3583\n148#9:3584\n148#9:3585\n148#9:3631\n158#9:3632\n148#9:3633\n148#9:3634\n98#10,3:1427\n101#10:1458\n98#10:1459\n94#10,7:1460\n101#10:1495\n105#10:1501\n98#10:1511\n95#10,6:1512\n101#10:1546\n105#10:1552\n105#10:1556\n98#10:1559\n96#10,5:1560\n101#10:1593\n98#10,3:1603\n101#10:1634\n105#10:1640\n105#10:1645\n98#10,3:1662\n101#10:1693\n98#10:1694\n94#10,7:1695\n101#10:1730\n105#10:1777\n98#10:1778\n94#10,7:1779\n101#10:1814\n105#10:1819\n105#10:1823\n98#10,3:2036\n101#10:2067\n98#10:2068\n94#10,7:2069\n101#10:2104\n105#10:2151\n105#10:2156\n98#10,3:2304\n101#10:2335\n98#10:2336\n94#10,7:2337\n101#10:2372\n105#10:2419\n105#10:2424\n98#10,3:2514\n101#10:2545\n98#10:2547\n94#10,7:2548\n101#10:2583\n105#10:2670\n98#10:2671\n94#10,7:2672\n101#10:2707\n105#10:2718\n105#10:2722\n98#10,3:2839\n101#10:2870\n105#10:2888\n98#10,3:3097\n101#10:3128\n105#10:3132\n98#10:3134\n95#10,6:3135\n101#10:3169\n98#10:3208\n96#10,5:3209\n101#10:3242\n105#10:3246\n105#10:3254\n98#10,3:3266\n101#10:3297\n105#10:3303\n98#10:3395\n94#10,7:3396\n101#10:3431\n105#10:3439\n98#10:3545\n94#10,7:3546\n101#10:3581\n105#10:3630\n1869#11:1594\n1870#11:1641\n1869#11:1652\n1870#11:1824\n774#11:1839\n865#11,2:1840\n774#11:1899\n865#11,2:1900\n774#11:2167\n865#11,2:2168\n1869#11:2463\n1870#11:2470\n1869#11:3320\n1870#11:3369\n1869#11:3394\n1870#11:3440\n1563#11:3479\n1634#11,3:3480\n774#11:3483\n865#11,2:3484\n1869#11:3582\n1870#11:3626\n71#12:1944\n69#12,5:1945\n74#12:1978\n78#12:1982\n71#12:2212\n69#12,5:2213\n74#12:2246\n78#12:2250\n71#12:2586\n69#12,5:2587\n74#12:2620\n78#12:2625\n71#12:2726\n68#12,6:2727\n74#12:2761\n78#12:2837\n71#12:2917\n69#12,5:2918\n74#12:2951\n71#12:3012\n69#12,5:3013\n74#12:3046\n78#12:3051\n78#12:3059\n71#12:3329\n68#12,6:3330\n74#12:3364\n78#12:3368\n71#12:3586\n68#12,6:3587\n74#12:3621\n78#12:3625\n79#13:2432\n112#13,2:2433\n79#13:2435\n112#13,2:2436\n81#14:2438\n107#14,2:2439\n81#14:2441\n107#14,2:2442\n81#14:2444\n107#14,2:2445\n81#14:2447\n107#14,2:2448\n81#14:2450\n107#14,2:2451\n81#14:2453\n107#14,2:2454\n81#14:2456\n107#14,2:2457\n81#14:2459\n107#14,2:2460\n143#15,12:3308\n143#15,12:3370\n143#15,12:3382\n143#15,12:3494\n*S KotlinDebug\n*F\n+ 1 FloatingAssistantOverlay.kt\ncom/example/ui/components/FloatingAssistantOverlayKt\n*L\n133#1:1309\n134#1:1310,6\n135#1:1316,6\n136#1:1322,6\n137#1:1328,6\n140#1:1334,6\n141#1:1340,6\n142#1:1346,3\n142#1:1350,3\n149#1:1353,6\n158#1:1359,6\n161#1:1365,6\n162#1:1371,6\n163#1:1377,6\n166#1:1383,6\n557#1:1504,6\n589#1:1596,6\n732#1:1655,6\n762#1:1830,6\n810#1:1836,3\n810#1:1842,3\n870#1:1886,6\n923#1:1896,3\n923#1:1902,3\n974#1:1987,6\n1128#1:2164,3\n1128#1:2170,3\n1179#1:2255,6\n332#1:2464,6\n250#1:2508,6\n298#1:2708,6\n361#1:2762,6\n363#1:2768,6\n365#1:2774,6\n369#1:2780,6\n385#1:2786,6\n387#1:2792,6\n395#1:2798,6\n396#1:2804,6\n397#1:2810,6\n405#1:2816,6\n406#1:2822,6\n407#1:2828,6\n433#1:2871,6\n442#1:2878,6\n194#1:2898,6\n196#1:2904,6\n207#1:2911,6\n226#1:2985,6\n227#1:2991,6\n464#1:3000,6\n482#1:3006,6\n696#1:3259,6\n852#1:3322,6\n514#1:1389\n514#1:1390,6\n514#1:1424\n740#1:1733\n740#1:1734,7\n740#1:1769\n740#1:1773\n514#1:1828\n821#1:1845\n821#1:1846,6\n821#1:1880\n821#1:1895\n929#1:1905\n929#1:1906,6\n929#1:1940\n929#1:1996\n1000#1:1997\n1000#1:1998,6\n1000#1:2032\n1019#1:2107\n1019#1:2108,7\n1019#1:2143\n1019#1:2147\n1000#1:2163\n1134#1:2173\n1134#1:2174,6\n1134#1:2208\n1134#1:2264\n1205#1:2265\n1205#1:2266,6\n1205#1:2300\n1224#1:2375\n1224#1:2376,7\n1224#1:2411\n1224#1:2415\n1205#1:2431\n241#1:2472\n241#1:2473,6\n241#1:2507\n281#1:2626\n281#1:2627,7\n281#1:2662\n281#1:2666\n241#1:2892\n216#1:2953,3\n216#1:2984\n216#1:3055\n623#1:3061\n623#1:3062,6\n623#1:3096\n651#1:3172\n651#1:3173,6\n651#1:3207\n651#1:3250\n623#1:3307\n1051#1:3442\n1051#1:3443,6\n1051#1:3477\n1051#1:3493\n1256#1:3507\n1256#1:3508,6\n1256#1:3542\n1256#1:3638\n514#1:1396,6\n514#1:1411,4\n514#1:1421,2\n520#1:1430,6\n520#1:1445,4\n520#1:1455,2\n530#1:1467,6\n530#1:1482,4\n530#1:1492,2\n530#1:1500\n552#1:1518,6\n552#1:1533,4\n552#1:1543,2\n552#1:1551\n520#1:1555\n570#1:1565,6\n570#1:1580,4\n570#1:1590,2\n584#1:1606,6\n584#1:1621,4\n584#1:1631,2\n584#1:1639\n570#1:1644\n726#1:1665,6\n726#1:1680,4\n726#1:1690,2\n737#1:1702,6\n737#1:1717,4\n737#1:1727,2\n740#1:1741,6\n740#1:1756,4\n740#1:1766,2\n740#1:1772\n737#1:1776\n745#1:1786,6\n745#1:1801,4\n745#1:1811,2\n745#1:1818\n726#1:1822\n514#1:1827\n821#1:1852,6\n821#1:1867,4\n821#1:1877,2\n821#1:1894\n929#1:1912,6\n929#1:1927,4\n929#1:1937,2\n933#1:1950,6\n933#1:1965,4\n933#1:1975,2\n933#1:1981\n929#1:1995\n1000#1:2004,6\n1000#1:2019,4\n1000#1:2029,2\n1006#1:2039,6\n1006#1:2054,4\n1006#1:2064,2\n1016#1:2076,6\n1016#1:2091,4\n1016#1:2101,2\n1019#1:2115,6\n1019#1:2130,4\n1019#1:2140,2\n1019#1:2146\n1016#1:2150\n1006#1:2155\n1000#1:2162\n1134#1:2180,6\n1134#1:2195,4\n1134#1:2205,2\n1138#1:2218,6\n1138#1:2233,4\n1138#1:2243,2\n1138#1:2249\n1134#1:2263\n1205#1:2272,6\n1205#1:2287,4\n1205#1:2297,2\n1211#1:2307,6\n1211#1:2322,4\n1211#1:2332,2\n1221#1:2344,6\n1221#1:2359,4\n1221#1:2369,2\n1224#1:2383,6\n1224#1:2398,4\n1224#1:2408,2\n1224#1:2414\n1221#1:2418\n1211#1:2423\n1205#1:2430\n241#1:2479,6\n241#1:2494,4\n241#1:2504,2\n247#1:2517,6\n247#1:2532,4\n247#1:2542,2\n262#1:2555,6\n262#1:2570,4\n262#1:2580,2\n266#1:2592,6\n266#1:2607,4\n266#1:2617,2\n266#1:2624\n281#1:2634,6\n281#1:2649,4\n281#1:2659,2\n281#1:2665\n262#1:2669\n296#1:2679,6\n296#1:2694,4\n296#1:2704,2\n296#1:2717\n247#1:2721\n352#1:2733,6\n352#1:2748,4\n352#1:2758,2\n352#1:2836\n422#1:2842,6\n422#1:2857,4\n422#1:2867,2\n422#1:2887\n241#1:2891\n203#1:2923,6\n203#1:2938,4\n203#1:2948,2\n216#1:2956,6\n216#1:2971,4\n216#1:2981,2\n453#1:3018,6\n453#1:3033,4\n453#1:3043,2\n453#1:3050\n216#1:3054\n203#1:3058\n623#1:3068,6\n623#1:3083,4\n623#1:3093,2\n624#1:3100,6\n624#1:3115,4\n624#1:3125,2\n624#1:3131\n645#1:3141,6\n645#1:3156,4\n645#1:3166,2\n651#1:3179,6\n651#1:3194,4\n651#1:3204,2\n652#1:3214,6\n652#1:3229,4\n652#1:3239,2\n652#1:3245\n651#1:3249\n645#1:3253\n690#1:3269,6\n690#1:3284,4\n690#1:3294,2\n690#1:3302\n623#1:3306\n848#1:3336,6\n848#1:3351,4\n848#1:3361,2\n848#1:3367\n1082#1:3403,6\n1082#1:3418,4\n1082#1:3428,2\n1082#1:3438\n1051#1:3449,6\n1051#1:3464,4\n1051#1:3474,2\n1051#1:3492\n1256#1:3514,6\n1256#1:3529,4\n1256#1:3539,2\n1264#1:3553,6\n1264#1:3568,4\n1264#1:3578,2\n1269#1:3593,6\n1269#1:3608,4\n1269#1:3618,2\n1269#1:3624\n1264#1:3629\n1256#1:3637\n514#1:1402,9\n514#1:1423\n520#1:1436,9\n520#1:1457\n530#1:1473,9\n530#1:1494\n530#1:1498,2\n552#1:1524,9\n552#1:1545\n552#1:1549,2\n520#1:1553,2\n570#1:1571,9\n570#1:1592\n584#1:1612,9\n584#1:1633\n584#1:1637,2\n570#1:1642,2\n726#1:1671,9\n726#1:1692\n737#1:1708,9\n737#1:1729\n740#1:1747,9\n740#1:1768\n740#1:1770,2\n737#1:1774,2\n745#1:1792,9\n745#1:1813\n745#1:1816,2\n726#1:1820,2\n514#1:1825,2\n821#1:1858,9\n821#1:1879\n821#1:1892,2\n929#1:1918,9\n929#1:1939\n933#1:1956,9\n933#1:1977\n933#1:1979,2\n929#1:1993,2\n1000#1:2010,9\n1000#1:2031\n1006#1:2045,9\n1006#1:2066\n1016#1:2082,9\n1016#1:2103\n1019#1:2121,9\n1019#1:2142\n1019#1:2144,2\n1016#1:2148,2\n1006#1:2153,2\n1000#1:2160,2\n1134#1:2186,9\n1134#1:2207\n1138#1:2224,9\n1138#1:2245\n1138#1:2247,2\n1134#1:2261,2\n1205#1:2278,9\n1205#1:2299\n1211#1:2313,9\n1211#1:2334\n1221#1:2350,9\n1221#1:2371\n1224#1:2389,9\n1224#1:2410\n1224#1:2412,2\n1221#1:2416,2\n1211#1:2421,2\n1205#1:2428,2\n241#1:2485,9\n241#1:2506\n247#1:2523,9\n247#1:2544\n262#1:2561,9\n262#1:2582\n266#1:2598,9\n266#1:2619\n266#1:2622,2\n281#1:2640,9\n281#1:2661\n281#1:2663,2\n262#1:2667,2\n296#1:2685,9\n296#1:2706\n296#1:2715,2\n247#1:2719,2\n352#1:2739,9\n352#1:2760\n352#1:2834,2\n422#1:2848,9\n422#1:2869\n422#1:2885,2\n241#1:2889,2\n203#1:2929,9\n203#1:2950\n216#1:2962,9\n216#1:2983\n453#1:3024,9\n453#1:3045\n453#1:3048,2\n216#1:3052,2\n203#1:3056,2\n623#1:3074,9\n623#1:3095\n624#1:3106,9\n624#1:3127\n624#1:3129,2\n645#1:3147,9\n645#1:3168\n651#1:3185,9\n651#1:3206\n652#1:3220,9\n652#1:3241\n652#1:3243,2\n651#1:3247,2\n645#1:3251,2\n690#1:3275,9\n690#1:3296\n690#1:3300,2\n623#1:3304,2\n848#1:3342,9\n848#1:3363\n848#1:3365,2\n1082#1:3409,9\n1082#1:3430\n1082#1:3436,2\n1051#1:3455,9\n1051#1:3476\n1051#1:3490,2\n1256#1:3520,9\n1256#1:3541\n1264#1:3559,9\n1264#1:3580\n1269#1:3599,9\n1269#1:3620\n1269#1:3622,2\n1264#1:3627,2\n1256#1:3635,2\n514#1:1415,6\n520#1:1449,6\n530#1:1486,6\n552#1:1537,6\n570#1:1584,6\n584#1:1625,6\n726#1:1684,6\n737#1:1721,6\n740#1:1760,6\n745#1:1805,6\n821#1:1871,6\n929#1:1931,6\n933#1:1969,6\n1000#1:2023,6\n1006#1:2058,6\n1016#1:2095,6\n1019#1:2134,6\n1134#1:2199,6\n1138#1:2237,6\n1205#1:2291,6\n1211#1:2326,6\n1221#1:2363,6\n1224#1:2402,6\n241#1:2498,6\n247#1:2536,6\n262#1:2574,6\n266#1:2611,6\n281#1:2653,6\n296#1:2698,6\n352#1:2752,6\n422#1:2861,6\n203#1:2942,6\n216#1:2975,6\n453#1:3037,6\n623#1:3087,6\n624#1:3119,6\n645#1:3160,6\n651#1:3198,6\n652#1:3233,6\n690#1:3288,6\n848#1:3355,6\n1082#1:3422,6\n1051#1:3468,6\n1256#1:3533,6\n1264#1:3572,6\n1269#1:3612,6\n523#1:1425\n525#1:1426\n537#1:1496\n547#1:1497\n554#1:1502\n556#1:1503\n558#1:1510\n561#1:1547\n562#1:1548\n567#1:1557\n572#1:1558\n587#1:1595\n590#1:1602\n598#1:1635\n600#1:1636\n611#1:1646\n619#1:1647\n619#1:1648\n620#1:1649\n714#1:1650\n723#1:1651\n729#1:1653\n730#1:1654\n733#1:1661\n738#1:1731\n739#1:1732\n747#1:1815\n761#1:1829\n836#1:1881\n839#1:1882\n843#1:1883\n865#1:1884\n869#1:1885\n936#1:1941\n938#1:1942\n939#1:1943\n951#1:1983\n966#1:1984\n969#1:1985\n973#1:1986\n1009#1:2033\n1011#1:2034\n1012#1:2035\n1017#1:2105\n1018#1:2106\n1036#1:2152\n1042#1:2157\n1047#1:2158\n1049#1:2159\n1141#1:2209\n1143#1:2210\n1144#1:2211\n1156#1:2251\n1171#1:2252\n1174#1:2253\n1178#1:2254\n1214#1:2301\n1216#1:2302\n1217#1:2303\n1222#1:2373\n1223#1:2374\n1241#1:2420\n1247#1:2425\n1252#1:2426\n1254#1:2427\n323#1:2462\n244#1:2471\n264#1:2546\n268#1:2584\n271#1:2585\n278#1:2621\n299#1:2714\n311#1:2723\n347#1:2724\n355#1:2725\n419#1:2838\n434#1:2877\n443#1:2884\n232#1:2893\n233#1:2894\n234#1:2895\n234#1:2896\n236#1:2897\n206#1:2910\n218#1:2952\n455#1:2997\n456#1:2998\n463#1:2999\n492#1:3047\n623#1:3060\n643#1:3133\n649#1:3170\n650#1:3171\n678#1:3255\n687#1:3256\n693#1:3257\n695#1:3258\n697#1:3265\n701#1:3298\n702#1:3299\n850#1:3321\n853#1:3328\n1088#1:3432\n1091#1:3433\n1093#1:3434\n1094#1:3435\n1051#1:3441\n1058#1:3478\n1073#1:3486\n1076#1:3487\n1077#1:3488\n1101#1:3489\n1256#1:3506\n1263#1:3543\n1265#1:3544\n1271#1:3583\n1273#1:3584\n1274#1:3585\n1286#1:3631\n1287#1:3632\n1288#1:3633\n1296#1:3634\n520#1:1427,3\n520#1:1458\n530#1:1459\n530#1:1460,7\n530#1:1495\n530#1:1501\n552#1:1511\n552#1:1512,6\n552#1:1546\n552#1:1552\n520#1:1556\n570#1:1559\n570#1:1560,5\n570#1:1593\n584#1:1603,3\n584#1:1634\n584#1:1640\n570#1:1645\n726#1:1662,3\n726#1:1693\n737#1:1694\n737#1:1695,7\n737#1:1730\n737#1:1777\n745#1:1778\n745#1:1779,7\n745#1:1814\n745#1:1819\n726#1:1823\n1006#1:2036,3\n1006#1:2067\n1016#1:2068\n1016#1:2069,7\n1016#1:2104\n1016#1:2151\n1006#1:2156\n1211#1:2304,3\n1211#1:2335\n1221#1:2336\n1221#1:2337,7\n1221#1:2372\n1221#1:2419\n1211#1:2424\n247#1:2514,3\n247#1:2545\n262#1:2547\n262#1:2548,7\n262#1:2583\n262#1:2670\n296#1:2671\n296#1:2672,7\n296#1:2707\n296#1:2718\n247#1:2722\n422#1:2839,3\n422#1:2870\n422#1:2888\n624#1:3097,3\n624#1:3128\n624#1:3132\n645#1:3134\n645#1:3135,6\n645#1:3169\n652#1:3208\n652#1:3209,5\n652#1:3242\n652#1:3246\n645#1:3254\n690#1:3266,3\n690#1:3297\n690#1:3303\n1082#1:3395\n1082#1:3396,7\n1082#1:3431\n1082#1:3439\n1264#1:3545\n1264#1:3546,7\n1264#1:3581\n1264#1:3630\n581#1:1594\n581#1:1641\n725#1:1652\n725#1:1824\n811#1:1839\n811#1:1840,2\n924#1:1899\n924#1:1900,2\n1129#1:2167\n1129#1:2168,2\n328#1:2463\n328#1:2470\n846#1:3320\n846#1:3369\n1079#1:3394\n1079#1:3440\n1069#1:3479\n1069#1:3480,3\n1070#1:3483\n1070#1:3484,2\n1268#1:3582\n1268#1:3626\n933#1:1944\n933#1:1945,5\n933#1:1978\n933#1:1982\n1138#1:2212\n1138#1:2213,5\n1138#1:2246\n1138#1:2250\n266#1:2586\n266#1:2587,5\n266#1:2620\n266#1:2625\n352#1:2726\n352#1:2727,6\n352#1:2761\n352#1:2837\n203#1:2917\n203#1:2918,5\n203#1:2951\n453#1:3012\n453#1:3013,5\n453#1:3046\n453#1:3051\n203#1:3059\n848#1:3329\n848#1:3330,6\n848#1:3364\n848#1:3368\n1269#1:3586\n1269#1:3587,6\n1269#1:3621\n1269#1:3625\n134#1:2432\n134#1:2433,2\n135#1:2435\n135#1:2436,2\n136#1:2438\n136#1:2439,2\n137#1:2441\n137#1:2442,2\n140#1:2444\n140#1:2445,2\n141#1:2447\n141#1:2448,2\n158#1:2450\n158#1:2451,2\n161#1:2453\n161#1:2454,2\n162#1:2456\n162#1:2457,2\n163#1:2459\n163#1:2460,2\n763#1:3308,12\n871#1:3370,12\n975#1:3382,12\n1180#1:3494,12\n*E\n"})
/* loaded from: FloatingAssistantOverlayKt.class */
public final class FloatingAssistantOverlayKt {

    /* compiled from: FloatingAssistantOverlay.kt */
    @Metadata(mv = {2, 2, 0}, k = 3, xi = 48)
    /* loaded from: FloatingAssistantOverlayKt$WhenMappings.class */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OverlayTab.values().length];
            try {
                iArr[OverlayTab.DRAFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[OverlayTab.OBJECTIVES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[OverlayTab.ITEMS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[OverlayTab.RUNES.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[OverlayTab.SPELLS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[OverlayTab.CD_TRACKER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[OverlayTab.DAMAGE_MATH.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final Unit FloatingAssistantOverlay$lambda$0(boolean $isVisible, Function0 $onDismiss, LaneRole $initialRole, Modifier $modifier, int $$changed, int $$default, Composer $composer, int $force) {
        FloatingAssistantOverlay($isVisible, $onDismiss, $initialRole, $modifier, $composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1), $$default);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$107(boolean $isVisible, Function0 $onDismiss, LaneRole $initialRole, Modifier $modifier, int $$changed, int $$default, Composer $composer, int $force) {
        FloatingAssistantOverlay($isVisible, $onDismiss, $initialRole, $modifier, $composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1), $$default);
        return Unit.INSTANCE;
    }

    private static final Unit OverlayDraftTabContent$lambda$135(LaneRole $activeRole, Function1 $onRoleChange, boolean $isFirstPick, Function1 $onFirstPickToggle, DraftAnalysisResult $analysis, Function1 $onLockChampion, Function0 $onSimulateScan, int $$changed, Composer $composer, int $force) {
        OverlayDraftTabContent($activeRole, $onRoleChange, $isFirstPick, $onFirstPickToggle, $analysis, $onLockChampion, $onSimulateScan, $composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1));
        return Unit.INSTANCE;
    }

    private static final Unit OverlayObjectivesTabContent$lambda$139(int $$changed, Composer $composer, int $force) {
        OverlayObjectivesTabContent($composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1));
        return Unit.INSTANCE;
    }

    private static final Unit OverlayItemsTabContent$lambda$151(String $searchQuery, Function1 $onSearchChange, ItemCategory $selectedCategory, Function1 $onCategoryChange, int $$changed, Composer $composer, int $force) {
        OverlayItemsTabContent($searchQuery, $onSearchChange, $selectedCategory, $onCategoryChange, $composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1));
        return Unit.INSTANCE;
    }

    private static final Unit OverlayRunesTabContent$lambda$175(Champion $lockedChampion, String $searchQuery, Function1 $onSearchChange, Function1 $onSelectChampion, Function0 $onClearChampion, int $$changed, Composer $composer, int $force) {
        OverlayRunesTabContent($lockedChampion, $searchQuery, $onSearchChange, $onSelectChampion, $onClearChampion, $composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1));
        return Unit.INSTANCE;
    }

    private static final Unit OverlaySpellsTabContent$lambda$196(Champion $lockedChampion, String $searchQuery, Function1 $onSearchChange, Function1 $onSelectChampion, Function0 $onClearChampion, int $$changed, Composer $composer, int $force) {
        OverlaySpellsTabContent($lockedChampion, $searchQuery, $onSearchChange, $onSelectChampion, $onClearChampion, $composer, RecomposeScopeImplKt.updateChangedFlags($$changed | 1));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    public static final void FloatingAssistantOverlay(boolean isVisible, @NotNull Function0<Unit> function0, @Nullable LaneRole initialRole, @Nullable Modifier modifier, @Nullable Composer $composer, int $changed, int i) {
        MutableFloatState mutableFloatState;
        MutableFloatState mutableFloatState2;
        MutableState mutableState;
        MutableState mutableState2;
        MutableState mutableState3;
        MutableState mutableState4;
        SnapshotStateList snapshotStateList;
        SnapshotStateList snapshotStateList2;
        MutableState mutableState5;
        MutableState mutableState6;
        MutableState mutableState7;
        MutableState mutableState8;
        DraftAnalysisResult draftAnalysisResult;
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Composer $composer2 = $composer.startRestartGroup(-874888184);
        ComposerKt.sourceInformation($composer2, "C(FloatingAssistantOverlay)P(1,3)132@6339L7,133@6370L36,134@6430L36,135@6495L33,136@6552L45,139@6640L40,140@6704L34,141@6763L292,148@7081L290,157@7451L44,160@7578L31,161@7642L48,162@7718L31,165@7813L286,179@8287L16907,174@8105L17089:FloatingAssistantOverlay.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(isVisible) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(initialRole == null ? -1 : ((Enum) initialRole).ordinal()) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(modifier) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if ((i & 4) != 0) {
                initialRole = LaneRole.MID;
            }
            if ((i & 8) != 0) {
                modifier = (Modifier) Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-874888184, $dirty, -1, "com.example.ui.components.FloatingAssistantOverlay (FloatingAssistantOverlay.kt:129)");
            }
            if (!isVisible) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    LaneRole laneRole = initialRole;
                    Modifier modifier2 = modifier;
                    endRestartGroup.updateScope((v6, v7) -> {
                        return FloatingAssistantOverlay$lambda$0(r1, r2, r3, r4, r5, r6, v6, v7);
                    });
                    return;
                }
                return;
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(CompositionLocalsKt.getLocalDensity());
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) consume;
            ComposerKt.sourceInformationMarkerStart($composer2, 1046054828, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue = $composer2.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                MutableFloatState mutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer2.updateRememberedValue(mutableFloatStateOf);
                mutableFloatState = mutableFloatStateOf;
            } else {
                mutableFloatState = rememberedValue;
            }
            MutableFloatState dragOffsetY$delegate = (MutableFloatState) mutableFloatState;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046056748, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue2 = $composer2.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                MutableFloatState mutableFloatStateOf2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer2.updateRememberedValue(mutableFloatStateOf2);
                mutableFloatState2 = mutableFloatStateOf2;
            } else {
                mutableFloatState2 = rememberedValue2;
            }
            MutableFloatState dragOffsetX$delegate = (MutableFloatState) mutableFloatState2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046058825, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue3 = $composer2.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(true, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default);
                mutableState = mutableStateOf$default;
            } else {
                mutableState = rememberedValue3;
            }
            MutableState showSpeechBubble$delegate = (MutableState) mutableState;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046060661, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue4 = $composer2.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default(OverlayTab.DRAFT, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default2);
                mutableState2 = mutableStateOf$default2;
            } else {
                mutableState2 = rememberedValue4;
            }
            MutableState selectedTab$delegate = (MutableState) mutableState2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046063472, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue5 = $composer2.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default3 = SnapshotStateKt.mutableStateOf$default(initialRole, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default3);
                mutableState3 = mutableStateOf$default3;
            } else {
                mutableState3 = rememberedValue5;
            }
            MutableState activeRole$delegate = (MutableState) mutableState3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046065514, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue6 = $composer2.rememberedValue();
            if (rememberedValue6 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default4 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default4);
                mutableState4 = mutableStateOf$default4;
            } else {
                mutableState4 = rememberedValue6;
            }
            MutableState isFirstPick$delegate = (MutableState) mutableState4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046067660, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue7 = $composer2.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                SnapshotStateList mutableStateListOf = SnapshotStateKt.mutableStateListOf();
                Champion championById = WildRiftRepository.INSTANCE.getChampionById("vayne");
                if (championById != null) {
                    mutableStateListOf.add(championById);
                }
                Champion championById2 = WildRiftRepository.INSTANCE.getChampionById("janna");
                if (championById2 != null) {
                    mutableStateListOf.add(championById2);
                }
                Champion championById3 = WildRiftRepository.INSTANCE.getChampionById("viego");
                if (championById3 != null) {
                    mutableStateListOf.add(championById3);
                }
                $composer2.updateRememberedValue(mutableStateListOf);
                snapshotStateList = mutableStateListOf;
            } else {
                snapshotStateList = rememberedValue7;
            }
            List list = (SnapshotStateList) snapshotStateList;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046077834, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue8 = $composer2.rememberedValue();
            if (rememberedValue8 == Composer.Companion.getEmpty()) {
                SnapshotStateList mutableStateListOf2 = SnapshotStateKt.mutableStateListOf();
                Champion championById4 = WildRiftRepository.INSTANCE.getChampionById("sett");
                if (championById4 != null) {
                    mutableStateListOf2.add(championById4);
                }
                Champion championById5 = WildRiftRepository.INSTANCE.getChampionById("vi");
                if (championById5 != null) {
                    mutableStateListOf2.add(championById5);
                }
                Champion championById6 = WildRiftRepository.INSTANCE.getChampionById("caitlyn");
                if (championById6 != null) {
                    mutableStateListOf2.add(championById6);
                }
                $composer2.updateRememberedValue(mutableStateListOf2);
                snapshotStateList2 = mutableStateListOf2;
            } else {
                snapshotStateList2 = rememberedValue8;
            }
            List list2 = (SnapshotStateList) snapshotStateList2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046089428, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue9 = $composer2.rememberedValue();
            if (rememberedValue9 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default5 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default5);
                mutableState5 = mutableStateOf$default5;
            } else {
                mutableState5 = rememberedValue9;
            }
            MutableState lockedChampion$delegate = (MutableState) mutableState5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046093479, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue10 = $composer2.rememberedValue();
            if (rememberedValue10 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default6 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default6);
                mutableState6 = mutableStateOf$default6;
            } else {
                mutableState6 = rememberedValue10;
            }
            MutableState itemSearchQuery$delegate = (MutableState) mutableState6;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046095544, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue11 = $composer2.rememberedValue();
            if (rememberedValue11 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default7 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default7);
                mutableState7 = mutableStateOf$default7;
            } else {
                mutableState7 = rememberedValue11;
            }
            MutableState itemSelectedCategory$delegate = (MutableState) mutableState7;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1046097959, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue12 = $composer2.rememberedValue();
            if (rememberedValue12 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default8 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(mutableStateOf$default8);
                mutableState8 = mutableStateOf$default8;
            } else {
                mutableState8 = rememberedValue12;
            }
            MutableState runeSearchQuery$delegate = (MutableState) mutableState8;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Enum FloatingAssistantOverlay$lambda$14 = FloatingAssistantOverlay$lambda$14(activeRole$delegate);
            boolean FloatingAssistantOverlay$lambda$17 = FloatingAssistantOverlay$lambda$17(isFirstPick$delegate);
            List list3 = list.toList();
            List list4 = list2.toList();
            ComposerKt.sourceInformationMarkerStart($composer2, 1046101254, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean changed = $composer2.changed(FloatingAssistantOverlay$lambda$14.ordinal()) | $composer2.changed(FloatingAssistantOverlay$lambda$17) | $composer2.changed(list3) | $composer2.changed(list4);
            Object rememberedValue13 = $composer2.rememberedValue();
            if (changed || rememberedValue13 == Composer.Companion.getEmpty()) {
                DraftAnalysisResult analyzeDraft$default = WildRiftRepository.analyzeDraft$default(WildRiftRepository.INSTANCE, FloatingAssistantOverlay$lambda$14(activeRole$delegate), list, list2, (Champion) null, FloatingAssistantOverlay$lambda$17(isFirstPick$delegate), (String) null, 40, (Object) null);
                $composer2.updateRememberedValue(analyzeDraft$default);
                draftAnalysisResult = analyzeDraft$default;
            } else {
                draftAnalysisResult = rememberedValue13;
            }
            DraftAnalysisResult draftAnalysis = (DraftAnalysisResult) draftAnalysisResult;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BoxWithConstraintsKt.BoxWithConstraints(TestTagKt.testTag(SizeKt.fillMaxSize$default(modifier, 0.0f, 1, (Object) null), "floating_assistant_overlay_root"), Alignment.Companion.getCenter(), false, ComposableLambdaKt.rememberComposableLambda(-1171260686, true, (v14, v15, v16) -> {
                return FloatingAssistantOverlay$lambda$106(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, v14, v15, v16);
            }, $composer2, 54), $composer2, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            LaneRole laneRole2 = initialRole;
            Modifier modifier3 = modifier;
            endRestartGroup2.updateScope((v6, v7) -> {
                return FloatingAssistantOverlay$lambda$107(r1, r2, r3, r4, r5, r6, v6, v7);
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float FloatingAssistantOverlay$lambda$2(MutableFloatState $dragOffsetY$delegate) {
        return ((FloatState) $dragOffsetY$delegate).getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float FloatingAssistantOverlay$lambda$5(MutableFloatState $dragOffsetX$delegate) {
        return ((FloatState) $dragOffsetX$delegate).getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FloatingAssistantOverlay$lambda$8(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void FloatingAssistantOverlay$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final OverlayTab FloatingAssistantOverlay$lambda$11(MutableState<OverlayTab> mutableState) {
        return (OverlayTab) ((State) mutableState).getValue();
    }

    private static final LaneRole FloatingAssistantOverlay$lambda$14(MutableState<LaneRole> mutableState) {
        return (LaneRole) ((State) mutableState).getValue();
    }

    private static final boolean FloatingAssistantOverlay$lambda$17(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void FloatingAssistantOverlay$lambda$18(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Champion FloatingAssistantOverlay$lambda$30(MutableState<Champion> mutableState) {
        return (Champion) ((State) mutableState).getValue();
    }

    private static final String FloatingAssistantOverlay$lambda$33(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final ItemCategory FloatingAssistantOverlay$lambda$36(MutableState<ItemCategory> mutableState) {
        return (ItemCategory) ((State) mutableState).getValue();
    }

    private static final String FloatingAssistantOverlay$lambda$39(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit FloatingAssistantOverlay$lambda$106(Density $density, MutableState $showSpeechBubble$delegate, MutableFloatState $dragOffsetX$delegate, MutableFloatState $dragOffsetY$delegate, Function0 $onDismiss, MutableState $activeRole$delegate, MutableState $selectedTab$delegate, DraftAnalysisResult $draftAnalysis, MutableState $isFirstPick$delegate, MutableState $lockedChampion$delegate, SnapshotStateList $scannedEnemies, MutableState $itemSearchQuery$delegate, MutableState $itemSelectedCategory$delegate, MutableState $runeSearchQuery$delegate, BoxWithConstraintsScope $this$BoxWithConstraints, Composer $composer, int $changed) {
        Function1 function1;
        Function1 function12;
        Function1 function13;
        FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1 floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1;
        Function0 function0;
        MutableInteractionSource mutableInteractionSource;
        Function0 function02;
        Intrinsics.checkNotNullParameter($this$BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C206@9248L287,202@9110L16078:FloatingAssistantOverlay.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed($this$BoxWithConstraints) ? 4 : 2;
        }
        if (($dirty & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1171260686, $dirty, -1, "com.example.ui.components.FloatingAssistantOverlay.<anonymous> (FloatingAssistantOverlay.kt:180)");
            }
            float screenWidthPx = $density.toPx-0680j_4($this$BoxWithConstraints.getMaxWidth-D9Ej5fM());
            float screenHeightPx = $density.toPx-0680j_4($this$BoxWithConstraints.getMaxHeight-D9Ej5fM());
            float maxSafeHorizontalOffset = screenWidthPx * 0.38f;
            float maxSafeVerticalOffset = screenHeightPx * 0.38f;
            if (FloatingAssistantOverlay$lambda$8($showSpeechBubble$delegate)) {
                $composer.startReplaceGroup(-712423328);
                ComposerKt.sourceInformation($composer, "193@8868L39,195@8973L72,188@8651L408");
                Modifier modifier = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), Color.copy-wmQWz5c$default(Color.Companion.getBlack-0d7_KjU(), 0.45f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (Shape) null, 2, (Object) null);
                ComposerKt.sourceInformationMarkerStart($composer, -300069063, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                Object rememberedValue = $composer.rememberedValue();
                if (rememberedValue == Composer.Companion.getEmpty()) {
                    MutableInteractionSource MutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    modifier = modifier;
                    $composer.updateRememberedValue(MutableInteractionSource);
                    mutableInteractionSource = MutableInteractionSource;
                } else {
                    mutableInteractionSource = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) mutableInteractionSource;
                Indication indication = null;
                boolean z = false;
                String str = null;
                Role role = null;
                ComposerKt.sourceInformationMarkerStart($composer, -300065670, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                Object rememberedValue2 = $composer.rememberedValue();
                if (rememberedValue2 == Composer.Companion.getEmpty()) {
                    Modifier modifier2 = modifier;
                    Function0 function03 = () -> {
                        return FloatingAssistantOverlay$lambda$106$lambda$46$lambda$45(r0);
                    };
                    modifier = modifier2;
                    mutableInteractionSource2 = mutableInteractionSource2;
                    indication = null;
                    z = false;
                    str = null;
                    role = null;
                    $composer.updateRememberedValue(function03);
                    function02 = function03;
                } else {
                    function02 = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                BoxKt.Box(ClickableKt.clickable-O2vRcR0$default(modifier, mutableInteractionSource2, indication, z, str, role, (Function0) function02, 28, (Object) null), $composer, 0);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-721005616);
                $composer.endReplaceGroup();
            }
            Modifier modifier3 = PaddingKt.padding-VpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(14), 0.0f, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer, -300056655, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean changed = $composer.changed(maxSafeHorizontalOffset) | $composer.changed(maxSafeVerticalOffset);
            Object rememberedValue3 = $composer.rememberedValue();
            if (changed || rememberedValue3 == Composer.Companion.getEmpty()) {
                Function1 function14 = (v4) -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$48$lambda$47(r0, r1, r2, r3, v4);
                };
                modifier3 = modifier3;
                $composer.updateRememberedValue(function14);
                function1 = function14;
            } else {
                function1 = rememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier testTag = TestTagKt.testTag(OffsetKt.offset(modifier3, (Function1) function1), "floating_assistant_container");
            Alignment center = Alignment.Companion.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, testTag);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (48 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (48 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -1793643009, "C215@9666L15512:FloatingAssistantOverlay.kt#qonjpd");
            Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
            Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(vertical, centerHorizontally, $composer, (14 & (438 >> 3)) | (112 & (438 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i4 = 6 | (896 & ((112 & (438 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor2);
            } else {
                $composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl($composer);
            Updater.set-impl(composer2, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i5 = 14 & (i4 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            int i6 = 6 | (112 & (438 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -852444169, "C225@10244L7,226@10360L7,227@10387L12549,223@10070L12866,463@23652L944,481@24632L92,452@23132L2032:FloatingAssistantOverlay.kt#qonjpd");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            boolean FloatingAssistantOverlay$lambda$8 = FloatingAssistantOverlay$lambda$8($showSpeechBubble$delegate);
            Modifier modifier4 = null;
            EnterTransition plus = EnterExitTransitionKt.fadeIn$default((FiniteAnimationSpec) null, 0.0f, 3, (Object) null).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E$default((FiniteAnimationSpec) null, 0.92f, 0L, 5, (Object) null));
            FiniteAnimationSpec finiteAnimationSpec = null;
            ComposerKt.sourceInformationMarkerStart($composer, -997339043, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue4 = $composer.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                Function1 function15 = (v0) -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$50$lambda$49(v0);
                };
                columnScope = columnScope;
                FloatingAssistantOverlay$lambda$8 = FloatingAssistantOverlay$lambda$8;
                modifier4 = null;
                plus = plus;
                finiteAnimationSpec = null;
                $composer.updateRememberedValue(function15);
                function12 = function15;
            } else {
                function12 = rememberedValue4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EnterTransition plus2 = plus.plus(EnterExitTransitionKt.slideInVertically$default(finiteAnimationSpec, (Function1) function12, 1, (Object) null));
            ExitTransition plus3 = EnterExitTransitionKt.fadeOut$default((FiniteAnimationSpec) null, 0.0f, 3, (Object) null).plus(EnterExitTransitionKt.scaleOut-L8ZKh-E$default((FiniteAnimationSpec) null, 0.92f, 0L, 5, (Object) null));
            FiniteAnimationSpec finiteAnimationSpec2 = null;
            ComposerKt.sourceInformationMarkerStart($composer, -997335331, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue5 = $composer.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                ColumnScope columnScope2 = columnScope;
                Function1 function16 = (v0) -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$52$lambda$51(v0);
                };
                columnScope = columnScope2;
                FloatingAssistantOverlay$lambda$8 = FloatingAssistantOverlay$lambda$8;
                modifier4 = modifier4;
                plus2 = plus2;
                plus3 = plus3;
                finiteAnimationSpec2 = null;
                $composer.updateRememberedValue(function16);
                function13 = function16;
            } else {
                function13 = rememberedValue5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            AnimatedVisibilityKt.AnimatedVisibility(columnScope, FloatingAssistantOverlay$lambda$8, modifier4, plus2, plus3.plus(EnterExitTransitionKt.slideOutVertically$default(finiteAnimationSpec2, (Function1) function13, 1, (Object) null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1601351070, true, (v13, v14, v15) -> {
                return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, v13, v14, v15);
            }, $composer, 54), $composer, 1600512 | (14 & i6), 18);
            Modifier modifier5 = BorderKt.border-xT4_qwU(BackgroundKt.background$default(ClipKt.clip(ShadowKt.shadow-s4CzXII$default(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(60)), Dp.constructor-impl(16), RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 28, (Object) null), RoundedCornerShapeKt.getCircleShape()), Brush.Companion.radialGradient-P_Vx-Ks$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(ColorKt.getHextechCyan()), Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(4278236376L)), Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(4278220726L))}), 0L, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl((float) 2.5d), ColorKt.getHextechGold(), RoundedCornerShapeKt.getCircleShape());
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -996909050, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean changed2 = $composer.changed(maxSafeVerticalOffset) | $composer.changed($onDismiss);
            Object rememberedValue6 = $composer.rememberedValue();
            if (changed2 || rememberedValue6 == Composer.Companion.getEmpty()) {
                FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1 floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$12 = new FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1(maxSafeVerticalOffset, $onDismiss, $dragOffsetY$delegate, $dragOffsetX$delegate, $showSpeechBubble$delegate, null);
                modifier5 = modifier5;
                unit = unit;
                $composer.updateRememberedValue(floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$12);
                floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1 = floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$12;
            } else {
                floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1 = rememberedValue6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(modifier5, unit, (Function2) floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$4$1);
            boolean z2 = false;
            String str2 = null;
            Role role2 = null;
            ComposerKt.sourceInformationMarkerStart($composer, -996878542, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue7 = $composer.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                Function0 function04 = () -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$102$lambda$101(r0);
                };
                pointerInput = pointerInput;
                z2 = false;
                str2 = null;
                role2 = null;
                $composer.updateRememberedValue(function04);
                function0 = function04;
            } else {
                function0 = rememberedValue7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier testTag2 = TestTagKt.testTag(ClickableKt.clickable-XHw0xAI$default(pointerInput, z2, str2, role2, (Function0) function0, 7, (Object) null), "floating_camera_bubble");
            Alignment center2 = Alignment.Companion.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, testTag2);
            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
            int i7 = 6 | (896 & ((112 & (48 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor3);
            } else {
                $composer.useNode();
            }
            Composer composer3 = Updater.constructor-impl($composer);
            Updater.set-impl(composer3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
            int i8 = 14 & (i7 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
            int i9 = 6 | (112 & (48 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 11393578, "C487@24881L265:FloatingAssistantOverlay.kt#qonjpd");
            IconKt.Icon-ww6aTOc(CameraAltKt.getCameraAlt(Icons.INSTANCE.getDefault()), "Escanear Draft & Overlay", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(30)), ColorKt.getHextechDarkBg(), $composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$46$lambda$45(MutableState $showSpeechBubble$delegate) {
        FloatingAssistantOverlay$lambda$9($showSpeechBubble$delegate, false);
        return Unit.INSTANCE;
    }

    private static final IntOffset FloatingAssistantOverlay$lambda$106$lambda$48$lambda$47(float $maxSafeHorizontalOffset, float $maxSafeVerticalOffset, MutableFloatState $dragOffsetX$delegate, MutableFloatState $dragOffsetY$delegate, Density $this$offset) {
        Intrinsics.checkNotNullParameter($this$offset, "$this$offset");
        return IntOffset.box-impl(IntOffsetKt.IntOffset(MathKt.roundToInt(RangesKt.coerceIn(FloatingAssistantOverlay$lambda$5($dragOffsetX$delegate), -$maxSafeHorizontalOffset, $maxSafeHorizontalOffset)), MathKt.roundToInt(RangesKt.coerceIn(FloatingAssistantOverlay$lambda$2($dragOffsetY$delegate), -$maxSafeVerticalOffset, $maxSafeVerticalOffset))));
    }

    private static final int FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$50$lambda$49(int it) {
        return -30;
    }

    private static final int FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$52$lambda$51(int it) {
        return -30;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99(MutableFloatState $dragOffsetX$delegate, MutableFloatState $dragOffsetY$delegate, MutableState $activeRole$delegate, MutableState $showSpeechBubble$delegate, MutableState $selectedTab$delegate, DraftAnalysisResult $draftAnalysis, MutableState $isFirstPick$delegate, MutableState $lockedChampion$delegate, SnapshotStateList $scannedEnemies, MutableState $itemSearchQuery$delegate, MutableState $itemSelectedCategory$delegate, MutableState $runeSearchQuery$delegate, Function0 $onDismiss, AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C236@10875L116,239@11014L11904,228@10409L12509:FloatingAssistantOverlay.kt#qonjpd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1601351070, $changed, -1, "com.example.ui.components.FloatingAssistantOverlay.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:228)");
        }
        CardKt.Card(TestTagKt.testTag(BorderKt.border-xT4_qwU(ShadowKt.shadow-s4CzXII$default(SizeKt.widthIn-VpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(420), 1, (Object) null), Dp.constructor-impl(24), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(18)), false, 0L, 0L, 28, (Object) null), Dp.constructor-impl((float) 1.5d), ColorKt.getHextechGold(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(18))), "tactical_advice_card"), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(18)), CardDefaults.INSTANCE.cardColors-ro_MJ88(Color.copy-wmQWz5c$default(ColorKt.getHextechDarkBg(), 0.98f, 0.0f, 0.0f, 0.0f, 14, (Object) null), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14), (CardElevation) null, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(813316460, true, (v13, v14, v15) -> {
            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, v13, v14, v15);
        }, $composer, 54), $composer, 196608, 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98(MutableFloatState $dragOffsetX$delegate, MutableFloatState $dragOffsetY$delegate, MutableState $activeRole$delegate, MutableState $showSpeechBubble$delegate, MutableState $selectedTab$delegate, DraftAnalysisResult $draftAnalysis, MutableState $isFirstPick$delegate, MutableState $lockedChampion$delegate, SnapshotStateList $scannedEnemies, MutableState $itemSearchQuery$delegate, MutableState $itemSelectedCategory$delegate, MutableState $runeSearchQuery$delegate, Function0 $onDismiss, ColumnScope $this$Card, Composer $composer, int $changed) {
        FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1 floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1;
        Function0 function0;
        Function1 function1;
        Function1 function12;
        Function0 function02;
        Function1 function13;
        Function1 function14;
        Function0 function03;
        Function1 function15;
        Function1 function16;
        Function0 function04;
        Function0 function05;
        Function1 function17;
        Function1 function18;
        Function1 function19;
        Function0 function06;
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C240@11040L11856:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(813316460, $changed, -1, "com.example.ui.components.FloatingAssistantOverlay.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:240)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(12));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (6 >> 3)) | (112 & (6 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (6 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (6 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 934516447, "C249@11469L481,246@11304L3635,310@14969L40,317@15346L537,326@15914L1130,313@15097L1947,346@17074L41,351@17345L4027,418@21430L41,421@21578L1292:FloatingAssistantOverlay.kt#qonjpd");
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 307234551, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1 floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$12 = new FloatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1($dragOffsetX$delegate, $dragOffsetY$delegate, null);
                fillMaxWidth$default = fillMaxWidth$default;
                unit = unit;
                $composer.updateRememberedValue(floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$12);
                floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1 = floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$12;
            } else {
                floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1 = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(fillMaxWidth$default, unit, (Function2) floatingAssistantOverlayKt$FloatingAssistantOverlay$2$4$1$3$1$1$1$1);
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer, (14 & (432 >> 3)) | (112 & (432 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, pointerInput);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i4 = 6 | (896 & ((112 & (432 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor2);
            } else {
                $composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl($composer);
            Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i5 = 14 & (i4 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i6 = 6 | (112 & (432 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -1314821883, "C261@12177L1950,295@14161L748:FloatingAssistantOverlay.kt#qonjpd");
            Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
            Arrangement.Horizontal horizontal = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontal, centerVertically2, $composer, (14 & (432 >> 3)) | (112 & (432 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
            int i7 = 6 | (896 & ((112 & (432 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor3);
            } else {
                $composer.useNode();
            }
            Composer composer3 = Updater.constructor-impl($composer);
            Updater.set-impl(composer3, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
            int i8 = 14 & (i7 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            int i9 = 6 | (112 & (432 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 1607660049, "C265@12425L896,280@13358L735:FloatingAssistantOverlay.kt#qonjpd");
            Modifier modifier3 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(28)), RoundedCornerShapeKt.getCircleShape()), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.15f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getHextechCyan(), RoundedCornerShapeKt.getCircleShape());
            Alignment center = Alignment.Companion.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier3);
            Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
            int i10 = 6 | (896 & ((112 & (48 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor4);
            } else {
                $composer.useNode();
            }
            Composer composer4 = Updater.constructor-impl($composer);
            Updater.set-impl(composer4, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer4.getInserting() || !Intrinsics.areEqual(composer4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composer4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composer4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.set-impl(composer4, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
            int i11 = 14 & (i10 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            int i12 = 6 | (112 & (48 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 1984095908, "C273@12940L343:FloatingAssistantOverlay.kt#qonjpd");
            IconKt.Icon-ww6aTOc(AutoAwesomeKt.getAutoAwesome(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), ColorKt.getHextechCyan(), $composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier4 = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (0 >> 3)) | (112 & (0 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier4);
            Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
            int i13 = 6 | (896 & ((112 & (0 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor5);
            } else {
                $composer.useNode();
            }
            Composer composer5 = Updater.constructor-impl($composer);
            Updater.set-impl(composer5, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer5, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer5.getInserting() || !Intrinsics.areEqual(composer5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composer5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composer5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.set-impl(composer5, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
            int i14 = 14 & (i13 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            int i15 = 6 | (112 & (0 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 809700355, "C281@13407L329,287@13777L278:FloatingAssistantOverlay.kt#qonjpd");
            TextKt.Text--4IGK_g("Wild Rift HUD Inteligente", (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(13.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199686, 0, 131026);
            TextKt.Text--4IGK_g("Superposición en Directo • " + FloatingAssistantOverlay$lambda$14($activeRole$delegate).getShortName(), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131058);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Alignment.Vertical centerVertically3 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier5 = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer, (14 & (384 >> 3)) | (112 & (384 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier5);
            Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
            int i16 = 6 | (896 & ((112 & (384 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor6);
            } else {
                $composer.useNode();
            }
            Composer composer6 = Updater.constructor-impl($composer);
            Updater.set-impl(composer6, rowMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer6, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer6.getInserting() || !Intrinsics.areEqual(composer6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                composer6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composer6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            }
            Updater.set-impl(composer6, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
            int i17 = 14 & (i16 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope3 = RowScopeInstance.INSTANCE;
            int i18 = 6 | (112 & (384 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 2020632284, "C297@14313L28,296@14251L624:FloatingAssistantOverlay.kt#qonjpd");
            ComposerKt.sourceInformationMarkerStart($composer, 619372403, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue2 = $composer.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                Function0 function07 = () -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$60$lambda$59$lambda$58$lambda$57(r0);
                };
                $composer.updateRememberedValue(function07);
                function0 = function07;
            } else {
                function0 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) function0, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(28)), false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$-977070118$app(), $composer, 196662, 28);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer, 6);
            TabRowKt.TabRow-pAZo6Ak(FloatingAssistantOverlay$lambda$11($selectedTab$delegate).ordinal(), (Modifier) null, ColorKt.getHextechSurface(), ColorKt.getHextechCyan(), ComposableLambdaKt.rememberComposableLambda(-248892258, true, (v1, v2, v3) -> {
                return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$61(r6, v1, v2, v3);
            }, $composer, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(549241502, true, (v1, v2) -> {
                return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$66(r8, v1, v2);
            }, $composer, 54), $composer, 1597440, 34);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), $composer, 6);
            Modifier modifier6 = SizeKt.heightIn-VpY3zN4(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(260), Dp.constructor-impl(360));
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifier6);
            Function0 constructor7 = ComposeUiNode.Companion.getConstructor();
            int i19 = 6 | (896 & ((112 & (6 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor7);
            } else {
                $composer.useNode();
            }
            Composer composer7 = Updater.constructor-impl($composer);
            Updater.set-impl(composer7, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer7, currentCompositionLocalMap7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash7 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer7.getInserting() || !Intrinsics.areEqual(composer7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                composer7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composer7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            }
            Updater.set-impl(composer7, materializeModifier7, ComposeUiNode.Companion.getSetModifier());
            int i20 = 14 & (i19 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
            int i21 = 6 | (112 & (6 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 1183331693, "C:FloatingAssistantOverlay.kt#qonjpd");
            switch (WhenMappings.$EnumSwitchMapping$0[FloatingAssistantOverlay$lambda$11($selectedTab$delegate).ordinal()]) {
                case 1:
                    $composer.startReplaceGroup(1183330855);
                    ComposerKt.sourceInformation($composer, "360@17863L19,362@18019L20,364@18172L206,368@18441L407,358@17711L1179");
                    LaneRole FloatingAssistantOverlay$lambda$14 = FloatingAssistantOverlay$lambda$14($activeRole$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, 38176931, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue3 = $composer.rememberedValue();
                    if (rememberedValue3 == Composer.Companion.getEmpty()) {
                        Function1 function110 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$68$lambda$67(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$14 = FloatingAssistantOverlay$lambda$14;
                        $composer.updateRememberedValue(function110);
                        function17 = function110;
                    } else {
                        function17 = rememberedValue3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function111 = (Function1) function17;
                    boolean FloatingAssistantOverlay$lambda$17 = FloatingAssistantOverlay$lambda$17($isFirstPick$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, 38181924, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue4 = $composer.rememberedValue();
                    if (rememberedValue4 == Composer.Companion.getEmpty()) {
                        LaneRole laneRole = FloatingAssistantOverlay$lambda$14;
                        Function1 function112 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$70$lambda$69(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$14 = laneRole;
                        function111 = function111;
                        FloatingAssistantOverlay$lambda$17 = FloatingAssistantOverlay$lambda$17;
                        $composer.updateRememberedValue(function112);
                        function18 = function112;
                    } else {
                        function18 = rememberedValue4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function113 = (Function1) function18;
                    DraftAnalysisResult draftAnalysisResult = $draftAnalysis;
                    ComposerKt.sourceInformationMarkerStart($composer, 38187006, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue5 = $composer.rememberedValue();
                    if (rememberedValue5 == Composer.Companion.getEmpty()) {
                        LaneRole laneRole2 = FloatingAssistantOverlay$lambda$14;
                        Function1 function114 = (v2) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$72$lambda$71(r0, r1, v2);
                        };
                        FloatingAssistantOverlay$lambda$14 = laneRole2;
                        function111 = function111;
                        FloatingAssistantOverlay$lambda$17 = FloatingAssistantOverlay$lambda$17;
                        function113 = function113;
                        draftAnalysisResult = draftAnalysisResult;
                        $composer.updateRememberedValue(function114);
                        function19 = function114;
                    } else {
                        function19 = rememberedValue5;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function115 = (Function1) function19;
                    ComposerKt.sourceInformationMarkerStart($composer, 38195815, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue6 = $composer.rememberedValue();
                    if (rememberedValue6 == Composer.Companion.getEmpty()) {
                        LaneRole laneRole3 = FloatingAssistantOverlay$lambda$14;
                        Function0 function08 = () -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$74$lambda$73(r0);
                        };
                        FloatingAssistantOverlay$lambda$14 = laneRole3;
                        function111 = function111;
                        FloatingAssistantOverlay$lambda$17 = FloatingAssistantOverlay$lambda$17;
                        function113 = function113;
                        draftAnalysisResult = draftAnalysisResult;
                        function115 = function115;
                        $composer.updateRememberedValue(function08);
                        function06 = function08;
                    } else {
                        function06 = rememberedValue6;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    OverlayDraftTabContent(FloatingAssistantOverlay$lambda$14, function111, FloatingAssistantOverlay$lambda$17, function113, draftAnalysisResult, function115, (Function0) function06, $composer, 1772592);
                    $composer.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                    break;
                case 2:
                    $composer.startReplaceGroup(1184606629);
                    ComposerKt.sourceInformation($composer, "378@19033L29");
                    OverlayObjectivesTabContent($composer, 0);
                    $composer.endReplaceGroup();
                    Unit unit3 = Unit.INSTANCE;
                    break;
                case 3:
                    $composer.startReplaceGroup(1184783949);
                    ComposerKt.sourceInformation($composer, "384@19360L24,386@19534L29,382@19200L405");
                    String FloatingAssistantOverlay$lambda$33 = FloatingAssistantOverlay$lambda$33($itemSearchQuery$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, 38224840, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue7 = $composer.rememberedValue();
                    if (rememberedValue7 == Composer.Companion.getEmpty()) {
                        Function1 function116 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$76$lambda$75(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$33 = FloatingAssistantOverlay$lambda$33;
                        $composer.updateRememberedValue(function116);
                        function15 = function116;
                    } else {
                        function15 = rememberedValue7;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function117 = (Function1) function15;
                    ItemCategory FloatingAssistantOverlay$lambda$36 = FloatingAssistantOverlay$lambda$36($itemSelectedCategory$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, 38230413, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue8 = $composer.rememberedValue();
                    if (rememberedValue8 == Composer.Companion.getEmpty()) {
                        String str = FloatingAssistantOverlay$lambda$33;
                        Function1 function118 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$78$lambda$77(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$33 = str;
                        function117 = function117;
                        FloatingAssistantOverlay$lambda$36 = FloatingAssistantOverlay$lambda$36;
                        $composer.updateRememberedValue(function118);
                        function16 = function118;
                    } else {
                        function16 = rememberedValue8;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    OverlayItemsTabContent(FloatingAssistantOverlay$lambda$33, function117, FloatingAssistantOverlay$lambda$36, (Function1) function16, $composer, 3120);
                    $composer.endReplaceGroup();
                    Unit unit4 = Unit.INSTANCE;
                    break;
                case 4:
                    $composer.startReplaceGroup(1185324930);
                    ComposerKt.sourceInformation($composer, "394@19980L24,395@20069L23,396@20156L25,391@19743L480");
                    Champion FloatingAssistantOverlay$lambda$30 = FloatingAssistantOverlay$lambda$30($lockedChampion$delegate);
                    String FloatingAssistantOverlay$lambda$39 = FloatingAssistantOverlay$lambda$39($runeSearchQuery$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, 38244680, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue9 = $composer.rememberedValue();
                    if (rememberedValue9 == Composer.Companion.getEmpty()) {
                        Function1 function119 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$80$lambda$79(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$30 = FloatingAssistantOverlay$lambda$30;
                        FloatingAssistantOverlay$lambda$39 = FloatingAssistantOverlay$lambda$39;
                        $composer.updateRememberedValue(function119);
                        function13 = function119;
                    } else {
                        function13 = rememberedValue9;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function120 = (Function1) function13;
                    ComposerKt.sourceInformationMarkerStart($composer, 38247527, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue10 = $composer.rememberedValue();
                    if (rememberedValue10 == Composer.Companion.getEmpty()) {
                        Champion champion = FloatingAssistantOverlay$lambda$30;
                        Function1 function121 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$82$lambda$81(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$30 = champion;
                        FloatingAssistantOverlay$lambda$39 = FloatingAssistantOverlay$lambda$39;
                        function120 = function120;
                        $composer.updateRememberedValue(function121);
                        function14 = function121;
                    } else {
                        function14 = rememberedValue10;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function122 = (Function1) function14;
                    ComposerKt.sourceInformationMarkerStart($composer, 38250313, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue11 = $composer.rememberedValue();
                    if (rememberedValue11 == Composer.Companion.getEmpty()) {
                        Champion champion2 = FloatingAssistantOverlay$lambda$30;
                        Function0 function09 = () -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$84$lambda$83(r0);
                        };
                        FloatingAssistantOverlay$lambda$30 = champion2;
                        FloatingAssistantOverlay$lambda$39 = FloatingAssistantOverlay$lambda$39;
                        function120 = function120;
                        function122 = function122;
                        $composer.updateRememberedValue(function09);
                        function03 = function09;
                    } else {
                        function03 = rememberedValue11;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    OverlayRunesTabContent(FloatingAssistantOverlay$lambda$30, FloatingAssistantOverlay$lambda$39, function120, function122, (Function0) function03, $composer, 28032);
                    $composer.endReplaceGroup();
                    Unit unit5 = Unit.INSTANCE;
                    break;
                case 5:
                    $composer.startReplaceGroup(1185939009);
                    ComposerKt.sourceInformation($composer, "404@20600L24,405@20689L23,406@20776L25,401@20362L481");
                    Champion FloatingAssistantOverlay$lambda$302 = FloatingAssistantOverlay$lambda$30($lockedChampion$delegate);
                    String FloatingAssistantOverlay$lambda$392 = FloatingAssistantOverlay$lambda$39($runeSearchQuery$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, 38264520, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue12 = $composer.rememberedValue();
                    if (rememberedValue12 == Composer.Companion.getEmpty()) {
                        Function1 function123 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$86$lambda$85(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$302 = FloatingAssistantOverlay$lambda$302;
                        FloatingAssistantOverlay$lambda$392 = FloatingAssistantOverlay$lambda$392;
                        $composer.updateRememberedValue(function123);
                        function1 = function123;
                    } else {
                        function1 = rememberedValue12;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function124 = (Function1) function1;
                    ComposerKt.sourceInformationMarkerStart($composer, 38267367, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue13 = $composer.rememberedValue();
                    if (rememberedValue13 == Composer.Companion.getEmpty()) {
                        Champion champion3 = FloatingAssistantOverlay$lambda$302;
                        Function1 function125 = (v1) -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$88$lambda$87(r0, v1);
                        };
                        FloatingAssistantOverlay$lambda$302 = champion3;
                        FloatingAssistantOverlay$lambda$392 = FloatingAssistantOverlay$lambda$392;
                        function124 = function124;
                        $composer.updateRememberedValue(function125);
                        function12 = function125;
                    } else {
                        function12 = rememberedValue13;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Function1 function126 = (Function1) function12;
                    ComposerKt.sourceInformationMarkerStart($composer, 38270153, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                    Object rememberedValue14 = $composer.rememberedValue();
                    if (rememberedValue14 == Composer.Companion.getEmpty()) {
                        Champion champion4 = FloatingAssistantOverlay$lambda$302;
                        Function0 function010 = () -> {
                            return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$90$lambda$89(r0);
                        };
                        FloatingAssistantOverlay$lambda$302 = champion4;
                        FloatingAssistantOverlay$lambda$392 = FloatingAssistantOverlay$lambda$392;
                        function124 = function124;
                        function126 = function126;
                        $composer.updateRememberedValue(function010);
                        function02 = function010;
                    } else {
                        function02 = rememberedValue14;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    OverlaySpellsTabContent(FloatingAssistantOverlay$lambda$302, FloatingAssistantOverlay$lambda$392, function124, function126, (Function0) function02, $composer, 28032);
                    $composer.endReplaceGroup();
                    Unit unit6 = Unit.INSTANCE;
                    break;
                case 6:
                    $composer.startReplaceGroup(1186544594);
                    ComposerKt.sourceInformation($composer, "410@20985L80");
                    CooldownTrackerPanelKt.CooldownTrackerPanel(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), true, $composer, 54, 0);
                    $composer.endReplaceGroup();
                    Unit unit7 = Unit.INSTANCE;
                    break;
                case 7:
                    $composer.startReplaceGroup(1186765252);
                    ComposerKt.sourceInformation($composer, "413@21208L62");
                    DamagePenetrationCalculatorKt.DamagePenetrationCalculator(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), false, $composer, 6, 2);
                    $composer.endReplaceGroup();
                    Unit unit8 = Unit.INSTANCE;
                    break;
                default:
                    $composer.startReplaceGroup(38171990);
                    $composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), $composer, 6);
            Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically4 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy4 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically4, $composer, (14 & (438 >> 3)) | (112 & (438 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap8 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier8 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default2);
            Function0 constructor8 = ComposeUiNode.Companion.getConstructor();
            int i22 = 6 | (896 & ((112 & (438 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor8);
            } else {
                $composer.useNode();
            }
            Composer composer8 = Updater.constructor-impl($composer);
            Updater.set-impl(composer8, rowMeasurePolicy4, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer8, currentCompositionLocalMap8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash8 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer8.getInserting() || !Intrinsics.areEqual(composer8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                composer8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                composer8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
            }
            Updater.set-impl(composer8, materializeModifier8, ComposeUiNode.Companion.getSetModifier());
            int i23 = 14 & (i22 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope4 = RowScopeInstance.INSTANCE;
            int i24 = 6 | (112 & (438 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -1002832284, "C427@21925L23,432@22232L15,426@21876L460,436@22418L19,441@22723L28,435@22369L471:FloatingAssistantOverlay.kt#qonjpd");
            String tr = TranslatorKt.tr("Detener Asistente", $composer, 6);
            long dangerRed = ColorKt.getDangerRed();
            long sp = TextUnitKt.getSp(12);
            FontWeight bold = FontWeight.Companion.getBold();
            Modifier modifier7 = Modifier.Companion;
            boolean z = false;
            String str2 = null;
            Role role = null;
            ComposerKt.sourceInformationMarkerStart($composer, -447980982, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue15 = $composer.rememberedValue();
            if (changed || rememberedValue15 == Composer.Companion.getEmpty()) {
                Function0 function011 = () -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$96$lambda$93$lambda$92(r0);
                };
                modifier7 = modifier7;
                z = false;
                str2 = null;
                role = null;
                $composer.updateRememberedValue(function011);
                function04 = function011;
            } else {
                function04 = rememberedValue15;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.Text--4IGK_g(tr, PaddingKt.padding-3ABfNKs(ClickableKt.clickable-XHw0xAI$default(modifier7, z, str2, role, (Function0) function04, 7, (Object) null), Dp.constructor-impl(8)), dangerRed, sp, (FontStyle) null, bold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 200064, 0, 131024);
            String tr2 = TranslatorKt.tr("Minimizar HUD", $composer, 6);
            long hextechCyan = ColorKt.getHextechCyan();
            long sp2 = TextUnitKt.getSp(12);
            FontWeight bold2 = FontWeight.Companion.getBold();
            Modifier modifier8 = Modifier.Companion;
            boolean z2 = false;
            String str3 = null;
            Role role2 = null;
            ComposerKt.sourceInformationMarkerStart($composer, -447965257, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue16 = $composer.rememberedValue();
            if (rememberedValue16 == Composer.Companion.getEmpty()) {
                Function0 function012 = () -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$96$lambda$95$lambda$94(r0);
                };
                modifier8 = modifier8;
                z2 = false;
                str3 = null;
                role2 = null;
                $composer.updateRememberedValue(function012);
                function05 = function012;
            } else {
                function05 = rememberedValue16;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.Text--4IGK_g(tr2, PaddingKt.padding-3ABfNKs(ClickableKt.clickable-XHw0xAI$default(modifier8, z2, str3, role2, (Function0) function05, 7, (Object) null), Dp.constructor-impl(8)), hextechCyan, sp2, (FontStyle) null, bold2, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131024);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$60$lambda$59$lambda$58$lambda$57(MutableState $showSpeechBubble$delegate) {
        FloatingAssistantOverlay$lambda$9($showSpeechBubble$delegate, false);
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$61(MutableState $selectedTab$delegate, List tabPositions, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
        ComposerKt.sourceInformation($composer, "C:FloatingAssistantOverlay.kt#qonjpd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-248892258, $changed, -1, "com.example.ui.components.FloatingAssistantOverlay.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:318)");
        }
        int size = tabPositions.size();
        int ordinal = FloatingAssistantOverlay$lambda$11($selectedTab$delegate).ordinal();
        if (0 <= ordinal ? ordinal < size : false) {
            $composer.startReplaceGroup(-1635590604);
            ComposerKt.sourceInformation($composer, "319@15506L305");
            TabRowDefaults.INSTANCE.SecondaryIndicator-9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.Companion, (TabPosition) tabPositions.get(FloatingAssistantOverlay$lambda$11($selectedTab$delegate).ordinal())), Dp.constructor-impl((float) 2.5d), ColorKt.getHextechGold(), $composer, 48 | (TabRowDefaults.$stable << 9), 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-1650929404);
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$66(MutableState $selectedTab$delegate, Composer $composer, int $changed) {
        Function0 function0;
        ComposerKt.sourceInformation($composer, "C*331@16210L21,332@16280L662,329@16092L888:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(549241502, $changed, -1, "com.example.ui.components.FloatingAssistantOverlay.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:327)");
            }
            for (Enum r0 : OverlayTab.getEntries()) {
                boolean z = FloatingAssistantOverlay$lambda$11($selectedTab$delegate) == r0;
                boolean z2 = z;
                ComposerKt.sourceInformationMarkerStart($composer, 394745903, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changed = $composer.changed(r0.ordinal());
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    Function0 function02 = () -> {
                        return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$66$lambda$65$lambda$63$lambda$62(r0, r1);
                    };
                    z2 = z2;
                    $composer.updateRememberedValue(function02);
                    function0 = function02;
                } else {
                    function0 = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TabKt.Tab-wqdebIU(z2, (Function0) function0, (Modifier) null, false, ComposableLambdaKt.rememberComposableLambda(1713562656, true, (v2, v3) -> {
                    return FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$66$lambda$65$lambda$64(r6, r7, v2, v3);
                }, $composer, 54), (Function2) null, 0L, 0L, (MutableInteractionSource) null, $composer, 24576, 492);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$66$lambda$65$lambda$63$lambda$62(OverlayTab $tab, MutableState $selectedTab$delegate) {
        $selectedTab$delegate.setValue($tab);
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$66$lambda$65$lambda$64(OverlayTab $tab, boolean $isSelected, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C333@16326L574:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1713562656, $changed, -1, "com.example.ui.components.FloatingAssistantOverlay.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:333)");
            }
            String title = $tab.getTitle();
            long sp = TextUnitKt.getSp(11);
            int i = TextOverflow.Companion.getEllipsis-gIe3tQ8();
            TextKt.Text--4IGK_g(title, (Modifier) null, $isSelected ? ColorKt.getHextechGold() : ColorKt.getTextMuted(), sp, (FontStyle) null, $isSelected ? FontWeight.Companion.getBold() : FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, i, false, 1, 0, (Function1) null, (TextStyle) null, $composer, 3072, 3120, 120786);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$68$lambda$67(MutableState $activeRole$delegate, LaneRole it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $activeRole$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$70$lambda$69(MutableState $isFirstPick$delegate, boolean it) {
        FloatingAssistantOverlay$lambda$18($isFirstPick$delegate, it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$72$lambda$71(MutableState $lockedChampion$delegate, MutableState $selectedTab$delegate, Champion champ) {
        Intrinsics.checkNotNullParameter(champ, "champ");
        $lockedChampion$delegate.setValue(champ);
        $selectedTab$delegate.setValue(OverlayTab.RUNES);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$74$lambda$73(SnapshotStateList $scannedEnemies) {
        $scannedEnemies.clear();
        List candidates = CollectionsKt.take(CollectionsKt.shuffled(WildRiftRepository.INSTANCE.getChampions()), 4);
        $scannedEnemies.addAll(candidates);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$76$lambda$75(MutableState $itemSearchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $itemSearchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$78$lambda$77(MutableState $itemSelectedCategory$delegate, ItemCategory it) {
        $itemSelectedCategory$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$80$lambda$79(MutableState $runeSearchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $runeSearchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$82$lambda$81(MutableState $lockedChampion$delegate, Champion it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $lockedChampion$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$84$lambda$83(MutableState $lockedChampion$delegate) {
        $lockedChampion$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$86$lambda$85(MutableState $runeSearchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $runeSearchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$88$lambda$87(MutableState $lockedChampion$delegate, Champion it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $lockedChampion$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$91$lambda$90$lambda$89(MutableState $lockedChampion$delegate) {
        $lockedChampion$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$96$lambda$93$lambda$92(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$99$lambda$98$lambda$97$lambda$96$lambda$95$lambda$94(MutableState $showSpeechBubble$delegate) {
        FloatingAssistantOverlay$lambda$9($showSpeechBubble$delegate, false);
        return Unit.INSTANCE;
    }

    private static final Unit FloatingAssistantOverlay$lambda$106$lambda$105$lambda$104$lambda$102$lambda$101(MutableState $showSpeechBubble$delegate) {
        FloatingAssistantOverlay$lambda$9($showSpeechBubble$delegate, !FloatingAssistantOverlay$lambda$8($showSpeechBubble$delegate));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final void OverlayDraftTabContent(LaneRole activeRole, Function1<? super LaneRole, Unit> function1, boolean isFirstPick, Function1<? super Boolean, Unit> function12, DraftAnalysisResult analysis, Function1<? super Champion, Unit> function13, Function0<Unit> function0, Composer $composer, int $changed) {
        Function0 function02;
        Function0 function03;
        Function0 function04;
        Composer $composer2 = $composer.startRestartGroup(818954821);
        ComposerKt.sourceInformation($composer2, "C(OverlayDraftTabContent)P(!1,5,2,3)516@25836L21,513@25745L10801:FloatingAssistantOverlay.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(((Enum) activeRole).ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(isFirstPick) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(analysis) ? 16384 : 8192;
        }
        if (($changed & 196608) == 0) {
            $dirty |= $composer2.changedInstance(function13) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(818954821, $dirty, -1, "com.example.ui.components.OverlayDraftTabContent (FloatingAssistantOverlay.kt:512)");
            }
            Modifier verticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, verticalScroll$default);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (0 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer composer = Updater.constructor-impl($composer2);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (0 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer2, 126138266, "C519@25934L2075,566@28019L40,569@28091L1899,610@30000L40,713@34764L40,717@34868L28,717@34899L24,716@34843L201,722@35053L40:FloatingAssistantOverlay.kt#qonjpd");
            Modifier modifier = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null), Dp.constructor-impl(10), Dp.constructor-impl(6));
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer2, (14 & (432 >> 3)) | (112 & (432 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifier);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i4 = 6 | (896 & ((112 & (432 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor2);
            } else {
                $composer2.useNode();
            }
            Composer composer2 = Updater.constructor-impl($composer2);
            Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i5 = 14 & (i4 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i6 = 6 | (112 & (432 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -1274966170, "C529@26337L869,556@27534L20,551@27253L746:FloatingAssistantOverlay.kt#qonjpd");
            Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer2, (14 & (384 >> 3)) | (112 & (384 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifier2);
            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
            int i7 = 6 | (896 & ((112 & (384 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor3);
            } else {
                $composer2.useNode();
            }
            Composer composer3 = Updater.constructor-impl($composer2);
            Updater.set-impl(composer3, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
            int i8 = 14 & (i7 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            int i9 = 6 | (112 & (384 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer2, 531123183, "C531@26440L14,530@26407L202,536@26626L39,540@26834L287,537@26682L510:FloatingAssistantOverlay.kt#qonjpd");
            TextKt.Text--4IGK_g(TranslatorKt.tr("1er Pick", $composer2, 6) + ":", (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(11.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
            SwitchKt.Switch(isFirstPick, function12, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(36)), (Function2) null, false, SwitchDefaults.INSTANCE.colors-V1nXRL4(ColorKt.getHextechGold(), Color.copy-wmQWz5c$default(ColorKt.getHextechGold(), 0.3f, 0.0f, 0.0f, 0.0f, 14, (Object) null), 0L, 0L, ColorKt.getTextMuted(), ColorKt.getHextechSurfaceVariant(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, SwitchDefaults.$stable << 18, 65484), (MutableInteractionSource) null, $composer2, 384 | (14 & ($dirty >> 6)) | (112 & ($dirty >> 6)), 88);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier3 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(Modifier.Companion, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8))), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.15f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.6f, 0.0f, 0.0f, 0.0f, 14, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)));
            boolean z = false;
            String str = null;
            Role role = null;
            ComposerKt.sourceInformationMarkerStart($composer2, -2119301261, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean z2 = ($dirty & 3670016) == 1048576;
            Object rememberedValue = $composer2.rememberedValue();
            if (z2 || rememberedValue == Composer.Companion.getEmpty()) {
                Function0 function05 = () -> {
                    return OverlayDraftTabContent$lambda$134$lambda$112$lambda$110$lambda$109(r0);
                };
                modifier3 = modifier3;
                z = false;
                str = null;
                role = null;
                $composer2.updateRememberedValue(function05);
                function02 = function05;
            } else {
                function02 = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier4 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-XHw0xAI$default(modifier3, z, str, role, (Function0) function02, 7, (Object) null), Dp.constructor-impl(8), Dp.constructor-impl(4));
            Alignment.Vertical centerVertically3 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer2, (14 & (384 >> 3)) | (112 & (384 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifier4);
            Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
            int i10 = 6 | (896 & ((112 & (384 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor4);
            } else {
                $composer2.useNode();
            }
            Composer composer4 = Updater.constructor-impl($composer2);
            Updater.set-impl(composer4, rowMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer4.getInserting() || !Intrinsics.areEqual(composer4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composer4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composer4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.set-impl(composer4, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
            int i11 = 14 & (i10 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope3 = RowScopeInstance.INSTANCE;
            int i12 = 6 | (112 & (384 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -1282550820, "C560@27716L107,561@27840L39,562@27901L14,562@27896L89:FloatingAssistantOverlay.kt#qonjpd");
            IconKt.Icon-ww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(13)), ColorKt.getHextechCyan(), $composer2, 432, 0);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer2, 6);
            TextKt.Text--4IGK_g(TranslatorKt.tr("Escanear", $composer2, 6), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(4)), Alignment.Companion.getTop(), $composer2, (14 & (54 >> 3)) | (112 & (54 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap5 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default);
            Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
            int i13 = 6 | (896 & ((112 & (54 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor5);
            } else {
                $composer2.useNode();
            }
            Composer composer5 = Updater.constructor-impl($composer2);
            Updater.set-impl(composer5, rowMeasurePolicy4, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer5, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer5.getInserting() || !Intrinsics.areEqual(composer5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composer5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composer5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.set-impl(composer5, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
            int i14 = 14 & (i13 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            int i15 = 6 | (112 & (54 >> 6));
            RowScope rowScope4 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 2074689592, "C:FloatingAssistantOverlay.kt#qonjpd");
            Map mapOf = MapsKt.mapOf(new Pair[]{TuplesKt.to(LaneRole.TOP, Integer.valueOf(R.drawable.ic_wr_role_solo)), TuplesKt.to(LaneRole.JUNGLE, Integer.valueOf(R.drawable.ic_wr_role_jungle)), TuplesKt.to(LaneRole.MID, Integer.valueOf(R.drawable.ic_wr_role_mid)), TuplesKt.to(LaneRole.ADC, Integer.valueOf(R.drawable.ic_wr_role_duo)), TuplesKt.to(LaneRole.SUPPORT, Integer.valueOf(R.drawable.ic_wr_role_support))});
            $composer2.startReplaceGroup(482579092);
            ComposerKt.sourceInformation($composer2, "*588@29024L22,583@28767L1199");
            for (Enum r0 : LaneRole.getEntries()) {
                boolean z3 = activeRole == r0;
                Integer num = (Integer) mapOf.get(r0);
                int intValue = num != null ? num.intValue() : R.drawable.ic_wr_role_mid;
                Modifier modifier5 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope4, Modifier.Companion, 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(6))), z3 ? ColorKt.getHextechCyan() : ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null);
                boolean z4 = false;
                String str2 = null;
                Role role2 = null;
                ComposerKt.sourceInformationMarkerStart($composer2, 1239789741, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changed = (($dirty & 112) == 32) | $composer2.changed(r0.ordinal());
                Object rememberedValue2 = $composer2.rememberedValue();
                if (changed || rememberedValue2 == Composer.Companion.getEmpty()) {
                    Function0 function06 = () -> {
                        return OverlayDraftTabContent$lambda$134$lambda$117$lambda$116$lambda$114$lambda$113(r0, r1);
                    };
                    modifier5 = modifier5;
                    z4 = false;
                    str2 = null;
                    role2 = null;
                    $composer2.updateRememberedValue(function06);
                    function04 = function06;
                } else {
                    function04 = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier6 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-XHw0xAI$default(modifier5, z4, str2, role2, (Function0) function04, 7, (Object) null), Dp.constructor-impl(2), Dp.constructor-impl(4));
                Alignment.Vertical centerVertically4 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getCenter(), centerVertically4, $composer2, (14 & (432 >> 3)) | (112 & (432 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap6 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer2, modifier6);
                Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
                int i16 = 6 | (896 & ((112 & (432 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor6);
                } else {
                    $composer2.useNode();
                }
                Composer composer6 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer6, rowMeasurePolicy5, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer6, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer6.getInserting() || !Intrinsics.areEqual(composer6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    composer6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                    composer6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                }
                Updater.set-impl(composer6, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
                int i17 = 14 & (i16 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope5 = RowScopeInstance.INSTANCE;
                int i18 = 6 | (112 & (432 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 578993692, "C594@29328L29,593@29288L288,599@29597L39,601@29694L18,600@29657L291:FloatingAssistantOverlay.kt#qonjpd");
                IconKt.Icon-ww6aTOc(PainterResources_androidKt.painterResource(intValue, $composer2, 0), r0.getShortName(), SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(13)), z3 ? ColorKt.getHextechDarkBg() : ColorKt.getHextechGold(), $composer2, 384, 0);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(3)), $composer2, 6);
                TextKt.Text--4IGK_g(TranslatorKt.tr(r0.getShortName(), $composer2, 0), (Modifier) null, z3 ? ColorKt.getHextechDarkBg() : ColorKt.getTextMuted(), TextUnitKt.getSp(9.5d), (FontStyle) null, z3 ? FontWeight.Companion.getBold() : FontWeight.Companion.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
            DraftRecommendation draftRecommendation = (DraftRecommendation) CollectionsKt.firstOrNull(analysis.getRecommendations());
            if (draftRecommendation != null) {
                $composer2.startReplaceGroup(130149851);
                ComposerKt.sourceInformation($composer2, "620@30424L43,621@30482L4262,615@30180L4564");
                CardKt.Card(BorderKt.border-xT4_qwU(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl((float) 1.2d), ColorKt.getHextechGold(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)), CardDefaults.INSTANCE.cardColors-ro_MJ88(ColorKt.getHextechSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), (CardElevation) null, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(893034568, true, (v3, v4, v5) -> {
                    return OverlayDraftTabContent$lambda$134$lambda$126(r7, r8, r9, v3, v4, v5);
                }, $composer2, 54), $composer2, 196608, 24);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(100081959);
                $composer2.endReplaceGroup();
            }
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
            TextKt.Text--4IGK_g(TranslatorKt.tr("Otras Alternativas en ", $composer2, 6) + TranslatorKt.tr(activeRole.getShortName(), $composer2, 0) + ":", (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer2, 6);
            $composer2.startReplaceGroup(835638479);
            ComposerKt.sourceInformation($composer2, "*731@35453L32,725@35173L1357");
            for (DraftRecommendation draftRecommendation2 : CollectionsKt.take(CollectionsKt.drop(analysis.getRecommendations(), 1), 3)) {
                Modifier modifier7 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(PaddingKt.padding-VpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(2), 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8))), Color.copy-wmQWz5c$default(ColorKt.getHextechSurfaceVariant(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (Shape) null, 2, (Object) null);
                boolean z5 = false;
                String str3 = null;
                Role role3 = null;
                ComposerKt.sourceInformationMarkerStart($composer2, -799947170, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changedInstance = (($dirty & 458752) == 131072) | $composer2.changedInstance(draftRecommendation2);
                Object rememberedValue3 = $composer2.rememberedValue();
                if (changedInstance || rememberedValue3 == Composer.Companion.getEmpty()) {
                    Function0 function07 = () -> {
                        return OverlayDraftTabContent$lambda$134$lambda$133$lambda$128$lambda$127(r0, r1);
                    };
                    modifier7 = modifier7;
                    z5 = false;
                    str3 = null;
                    role3 = null;
                    $composer2.updateRememberedValue(function07);
                    function03 = function07;
                } else {
                    function03 = rememberedValue3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier8 = PaddingKt.padding-3ABfNKs(ClickableKt.clickable-XHw0xAI$default(modifier7, z5, str3, role3, (Function0) function03, 7, (Object) null), Dp.constructor-impl(6));
                Alignment.Vertical centerVertically5 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically5, $composer2, (14 & (432 >> 3)) | (112 & (432 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap7 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier7 = ComposedModifierKt.materializeModifier($composer2, modifier8);
                Function0 constructor7 = ComposeUiNode.Companion.getConstructor();
                int i19 = 6 | (896 & ((112 & (432 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor7);
                } else {
                    $composer2.useNode();
                }
                Composer composer7 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer7, rowMeasurePolicy6, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer7, currentCompositionLocalMap7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash7 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer7.getInserting() || !Intrinsics.areEqual(composer7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                    composer7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                    composer7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
                }
                Updater.set-impl(composer7, materializeModifier7, ComposeUiNode.Companion.getSetModifier());
                int i20 = 14 & (i19 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope6 = RowScopeInstance.INSTANCE;
                int i21 = 6 | (112 & (432 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -185199648, "C736@35683L486,744@36186L330:FloatingAssistantOverlay.kt#qonjpd");
                Alignment.Vertical centerVertically6 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier9 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy7 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically6, $composer2, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap8 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier8 = ComposedModifierKt.materializeModifier($composer2, modifier9);
                Function0 constructor8 = ComposeUiNode.Companion.getConstructor();
                int i22 = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor8);
                } else {
                    $composer2.useNode();
                }
                Composer composer8 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer8, rowMeasurePolicy7, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer8, currentCompositionLocalMap8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash8 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer8.getInserting() || !Intrinsics.areEqual(composer8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                    composer8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                    composer8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                }
                Updater.set-impl(composer8, materializeModifier8, ComposeUiNode.Companion.getSetModifier());
                int i23 = 14 & (i22 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope7 = RowScopeInstance.INSTANCE;
                int i24 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1520866131, "C737@35757L76,738@35854L39,739@35914L237:FloatingAssistantOverlay.kt#qonjpd");
                ChampionAvatarKt.ChampionAvatar-DzVHIIc(draftRecommendation2.getChampion(), Dp.constructor-impl(32), false, (Modifier) null, $composer2, 432, 8);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Modifier modifier10 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash9 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap9 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier9 = ComposedModifierKt.materializeModifier($composer2, modifier10);
                Function0 constructor9 = ComposeUiNode.Companion.getConstructor();
                int i25 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor9);
                } else {
                    $composer2.useNode();
                }
                Composer composer9 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer9, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer9, currentCompositionLocalMap9, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash9 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer9.getInserting() || !Intrinsics.areEqual(composer9.rememberedValue(), Integer.valueOf(currentCompositeKeyHash9))) {
                    composer9.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash9));
                    composer9.apply(Integer.valueOf(currentCompositeKeyHash9), setCompositeKeyHash9);
                }
                Updater.set-impl(composer9, materializeModifier9, ComposeUiNode.Companion.getSetModifier());
                int i26 = 14 & (i25 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                int i27 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -860535183, "C740@35947L92,741@36069L22,741@36064L65:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(draftRecommendation2.getChampion().getName(), (Modifier) null, ColorKt.getTextPrimary(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                TextKt.Text--4IGK_g(TranslatorKt.tr(draftRecommendation2.getAdvantageBadge(), $composer2, 0), (Modifier) null, ColorKt.getTextMuted(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Alignment.Vertical centerVertically7 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier11 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy8 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically7, $composer2, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash10 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap10 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier10 = ComposedModifierKt.materializeModifier($composer2, modifier11);
                Function0 constructor10 = ComposeUiNode.Companion.getConstructor();
                int i28 = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor10);
                } else {
                    $composer2.useNode();
                }
                Composer composer10 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer10, rowMeasurePolicy8, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer10, currentCompositionLocalMap10, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash10 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer10.getInserting() || !Intrinsics.areEqual(composer10.rememberedValue(), Integer.valueOf(currentCompositeKeyHash10))) {
                    composer10.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash10));
                    composer10.apply(Integer.valueOf(currentCompositeKeyHash10), setCompositeKeyHash10);
                }
                Updater.set-impl(composer10, materializeModifier10, ComposeUiNode.Companion.getSetModifier());
                int i29 = 14 & (i28 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope8 = RowScopeInstance.INSTANCE;
                int i30 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1970150232, "C745@36260L105,746@36386L112:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g("WR: " + draftRecommendation2.getEstimatedWinrate() + "%", (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                IconKt.Icon-ww6aTOc(NavigateNextKt.getNavigateNext(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), ColorKt.getHextechGold(), $composer2, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope((v8, v9) -> {
                return OverlayDraftTabContent$lambda$135(r1, r2, r3, r4, r5, r6, r7, r8, v8, v9);
            });
        }
    }

    private static final Unit OverlayDraftTabContent$lambda$134$lambda$112$lambda$110$lambda$109(Function0 $onSimulateScan) {
        $onSimulateScan.invoke();
        return Unit.INSTANCE;
    }

    private static final Unit OverlayDraftTabContent$lambda$134$lambda$117$lambda$116$lambda$114$lambda$113(Function1 $onRoleChange, LaneRole $role) {
        $onRoleChange.invoke($role);
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit OverlayDraftTabContent$lambda$134$lambda$126(DraftRecommendation $topRec, Function1 $onLockChampion, boolean $isFirstPick, ColumnScope $this$Card, Composer $composer, int $changed) {
        String str;
        Function0 function0;
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C622@30500L4230:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(893034568, $changed, -1, "com.example.ui.components.OverlayDraftTabContent.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:622)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (6 >> 3)) | (112 & (6 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (6 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (6 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 1961518706, "C623@30565L872,642@31459L40,644@31521L1603,677@33146L40,679@33208L230,686@33460L40,695@33929L35,689@33583L1129:FloatingAssistantOverlay.kt#qonjpd");
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer, (14 & (438 >> 3)) | (112 & (438 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i4 = 6 | (896 & ((112 & (438 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor2);
            } else {
                $composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl($composer);
            Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i5 = 14 & (i4 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i6 = 6 | (112 & (438 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 66594705, "C628@30823L309,634@31157L258:FloatingAssistantOverlay.kt#qonjpd");
            if ($isFirstPick) {
                $composer.startReplaceGroup(1941812161);
                ComposerKt.sourceInformation($composer, "629@30888L24");
                $composer.endReplaceGroup();
                str = "★ " + TranslatorKt.tr("1ª Elección Segura", $composer, 6);
            } else {
                $composer.startReplaceGroup(1941813348);
                ComposerKt.sourceInformation($composer, "629@30925L27");
                $composer.endReplaceGroup();
                str = "★ " + TranslatorKt.tr("MEJOR OPCIÓN ABSOLUTA", $composer, 6);
            }
            TextKt.Text--4IGK_g(str, (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBlack(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            TextKt.Text--4IGK_g("WR Est.: " + $topRec.getEstimatedWinrate() + "%", (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
            Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
            Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, (14 & (390 >> 3)) | (112 & (390 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default2);
            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
            int i7 = 6 | (896 & ((112 & (390 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor3);
            } else {
                $composer.useNode();
            }
            Composer composer3 = Updater.constructor-impl($composer);
            Updater.set-impl(composer3, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
            int i8 = 14 & (i7 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            int i9 = 6 | (112 & (390 >> 6));
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1387303221, "C648@31705L56,649@31786L40,650@31851L1251:FloatingAssistantOverlay.kt#qonjpd");
            ChampionAvatarKt.ChampionAvatar-DzVHIIc($topRec.getChampion(), Dp.constructor-impl(44), false, (Modifier) null, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), $composer, 6);
            Modifier weight$default = RowScope.weight$default(rowScope2, Modifier.Companion, 1.0f, false, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (0 >> 3)) | (112 & (0 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer, weight$default);
            Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
            int i10 = 6 | (896 & ((112 & (0 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor4);
            } else {
                $composer.useNode();
            }
            Composer composer4 = Updater.constructor-impl($composer);
            Updater.set-impl(composer4, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer4.getInserting() || !Intrinsics.areEqual(composer4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composer4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composer4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.set-impl(composer4, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
            int i11 = 14 & (i10 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            int i12 = 6 | (112 & (0 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 947541712, "C651@31920L856,669@32850L25,668@32805L271:FloatingAssistantOverlay.kt#qonjpd");
            Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), Alignment.Companion.getTop(), $composer, (14 & (54 >> 3)) | (112 & (54 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default3);
            Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
            int i13 = 6 | (896 & ((112 & (54 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor5);
            } else {
                $composer.useNode();
            }
            Composer composer5 = Updater.constructor-impl($composer);
            Updater.set-impl(composer5, rowMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer5, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer5.getInserting() || !Intrinsics.areEqual(composer5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composer5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composer5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.set-impl(composer5, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
            int i14 = 14 & (i13 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope3 = RowScopeInstance.INSTANCE;
            int i15 = 6 | (112 & (54 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -1382693360, "C655@32138L282,661@32453L293:FloatingAssistantOverlay.kt#qonjpd");
            TextKt.Text--4IGK_g($topRec.getChampion().getName(), (Modifier) null, ColorKt.getTextPrimary(), TextUnitKt.getSp(13.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            TextKt.Text--4IGK_g("Tier " + $topRec.getChampion().getTier(), (Modifier) null, ColorKt.getTierSPlusColor(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 200064, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.Text--4IGK_g(TranslatorKt.tr($topRec.getAdvantageBadge(), $composer, 0), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
            TextKt.Text--4IGK_g($topRec.getTacticalReason(), (Modifier) null, Color.copy-wmQWz5c$default(ColorKt.getTextPrimary(), 0.9f, 0.0f, 0.0f, 0.0f, 14, (Object) null), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(15), 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 6, 130034);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer, 6);
            Modifier modifier2 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8))), Color.copy-wmQWz5c$default(ColorKt.getHextechGold(), 0.15f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getHextechGold(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)));
            boolean z = false;
            String str2 = null;
            Role role = null;
            ComposerKt.sourceInformationMarkerStart($composer, 201925665, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean changed = $composer.changed($onLockChampion) | $composer.changedInstance($topRec);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                Function0 function02 = () -> {
                    return OverlayDraftTabContent$lambda$134$lambda$126$lambda$125$lambda$123$lambda$122(r0, r1);
                };
                modifier2 = modifier2;
                z = false;
                str2 = null;
                role = null;
                $composer.updateRememberedValue(function02);
                function0 = function02;
            } else {
                function0 = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier3 = PaddingKt.padding-VpY3zN4$default(ClickableKt.clickable-XHw0xAI$default(modifier2, z, str2, role, (Function0) function0, 7, (Object) null), 0.0f, Dp.constructor-impl(6), 1, (Object) null);
            Arrangement.Horizontal center = Arrangement.INSTANCE.getCenter();
            Alignment.Vertical centerVertically3 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy4 = RowKt.rowMeasurePolicy(center, centerVertically3, $composer, (14 & (432 >> 3)) | (112 & (432 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier3);
            Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
            int i16 = 6 | (896 & ((112 & (432 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor6);
            } else {
                $composer.useNode();
            }
            Composer composer6 = Updater.constructor-impl($composer);
            Updater.set-impl(composer6, rowMeasurePolicy4, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer6, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer6.getInserting() || !Intrinsics.areEqual(composer6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                composer6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composer6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            }
            Updater.set-impl(composer6, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
            int i17 = 14 & (i16 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope4 = RowScopeInstance.INSTANCE;
            int i18 = 6 | (112 & (432 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, 967155942, "C700@34207L105,701@34337L39,703@34442L11,703@34486L25,702@34401L289:FloatingAssistantOverlay.kt#qonjpd");
            IconKt.Icon-ww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(14)), ColorKt.getHextechGold(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
            TextKt.Text--4IGK_g(TranslatorKt.tr("Fijar", $composer, 6) + " " + $topRec.getChampion().getName() + " " + TranslatorKt.tr("y Ver Runas / Build", $composer, 6), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit OverlayDraftTabContent$lambda$134$lambda$126$lambda$125$lambda$123$lambda$122(Function1 $onLockChampion, DraftRecommendation $topRec) {
        $onLockChampion.invoke($topRec.getChampion());
        return Unit.INSTANCE;
    }

    private static final Unit OverlayDraftTabContent$lambda$134$lambda$133$lambda$128$lambda$127(Function1 $onLockChampion, DraftRecommendation $rec) {
        $onLockChampion.invoke($rec.getChampion());
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final void OverlayObjectivesTabContent(Composer $composer, int $changed) {
        Function1 function1;
        Composer $composer2 = $composer.startRestartGroup(-1463215703);
        ComposerKt.sourceInformation($composer2, "C(OverlayObjectivesTabContent)761@36932L1829,758@36814L1947:FloatingAssistantOverlay.kt#qonjpd");
        if ($changed == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1463215703, $changed, -1, "com.example.ui.components.OverlayObjectivesTabContent (FloatingAssistantOverlay.kt:757)");
            }
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
            LazyListState lazyListState = null;
            PaddingValues paddingValues = null;
            boolean z = false;
            Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(6));
            Alignment.Horizontal horizontal = null;
            FlingBehavior flingBehavior = null;
            boolean z2 = false;
            ComposerKt.sourceInformationMarkerStart($composer2, -1695977266, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            Object rememberedValue = $composer2.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                Function1 function12 = FloatingAssistantOverlayKt::OverlayObjectivesTabContent$lambda$138$lambda$137;
                fillMaxSize$default = fillMaxSize$default;
                lazyListState = null;
                paddingValues = null;
                z = false;
                vertical = vertical;
                horizontal = null;
                flingBehavior = null;
                z2 = false;
                $composer2.updateRememberedValue(function12);
                function1 = function12;
            } else {
                function1 = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyColumn(fillMaxSize$default, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, z2, (Function1) function1, $composer2, 100687878, 238);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope((v1, v2) -> {
                return OverlayObjectivesTabContent$lambda$139(r1, v1, v2);
            });
        }
    }

    private static final Unit OverlayObjectivesTabContent$lambda$138$lambda$137(LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final List mapObjectives = WildRiftRepository.INSTANCE.getMapObjectives();
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayObjectivesTabContent$lambda$138$lambda$137$$inlined$items$default$1
            @Nullable
            public final Void invoke(MapObjectiveItem mapObjectiveItem) {
                return null;
            }

            /* renamed from: invoke  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object m3invoke(Object p1) {
                return invoke((MapObjectiveItem) p1);
            }
        };
        $this$LazyColumn.items(mapObjectives.size(), (Function1) null, new Function1<Integer, Object>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayObjectivesTabContent$lambda$138$lambda$137$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            @Nullable
            public final Object invoke(int index) {
                return function1.invoke(mapObjectives.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayObjectivesTabContent$lambda$138$lambda$137$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            @Composable
            public final void invoke(@NotNull LazyItemScope $this$items, int it, @Nullable Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (($dirty & 147) == 146 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                int i = 14 & $dirty;
                final MapObjectiveItem mapObjectiveItem = (MapObjectiveItem) mapObjectives.get(it);
                $composer.startReplaceGroup(-591314526);
                ComposerKt.sourceInformation($composer, "C*766@37150L43,768@37300L1445,763@37003L1742:FloatingAssistantOverlay.kt#qonjpd");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10)), CardDefaults.INSTANCE.cardColors-ro_MJ88(ColorKt.getHextechSurface(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14), (CardElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1), ColorKt.getHextechCardBorder()), ComposableLambdaKt.rememberComposableLambda(121879237, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayObjectivesTabContent$1$1$1$1
                    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                        invoke((ColumnScope) p1, (Composer) p2, ((Number) p3).intValue());
                        return Unit.INSTANCE;
                    }

                    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
                    @Composable
                    public final void invoke(ColumnScope $this$Card, Composer $composer2, int $changed2) {
                        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
                        ComposerKt.sourceInformation($composer2, "C769@37318L1413:FloatingAssistantOverlay.kt#qonjpd");
                        if (($changed2 & 17) == 16 && $composer2.getSkipping()) {
                            $composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(121879237, $changed2, -1, "com.example.ui.components.OverlayObjectivesTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:769)");
                        }
                        Modifier modifier = PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10));
                        MapObjectiveItem mapObjectiveItem2 = mapObjectiveItem;
                        ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (6 >> 3)) | (112 & (6 >> 3)));
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, modifier);
                        Function0 constructor = ComposeUiNode.Companion.getConstructor();
                        int i2 = 6 | (896 & ((112 & (6 << 3)) << 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor);
                        } else {
                            $composer2.useNode();
                        }
                        Composer composer = Updater.constructor-impl($composer2);
                        Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                        int i3 = 14 & (i2 >> 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                        int i4 = 6 | (112 & (6 >> 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, 565904540, "C772@37421L939,788@38381L40,789@38450L17,789@38442L86,790@38549L40,791@38610L103:FloatingAssistantOverlay.kt#qonjpd");
                        Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                        Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                        Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer2, (14 & (438 >> 3)) | (112 & (438 >> 3)));
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default);
                        Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                        int i5 = 6 | (896 & ((112 & (438 << 3)) << 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor2);
                        } else {
                            $composer2.useNode();
                        }
                        Composer composer2 = Updater.constructor-impl($composer2);
                        Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                        int i6 = 14 & (i5 >> 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScope rowScope = RowScopeInstance.INSTANCE;
                        int i7 = 6 | (112 & (438 >> 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -2023002991, "C777@37679L85,778@37789L549:FloatingAssistantOverlay.kt#qonjpd");
                        TextKt.Text--4IGK_g(mapObjectiveItem2.getName(), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(12.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                        Modifier modifier2 = PaddingKt.padding-VpY3zN4(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(Modifier.Companion, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(4))), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.15f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (Shape) null, 2, (Object) null), Dp.constructor-impl((float) 0.5d), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(4))), Dp.constructor-impl(6), Dp.constructor-impl(2));
                        ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifier2);
                        Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                        int i8 = 6 | (896 & ((112 & (0 << 3)) << 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor3);
                        } else {
                            $composer2.useNode();
                        }
                        Composer composer3 = Updater.constructor-impl($composer2);
                        Updater.set-impl(composer3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                            composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                            composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                        }
                        Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                        int i9 = 14 & (i8 >> 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScope boxScope = BoxScopeInstance.INSTANCE;
                        int i10 = 6 | (112 & (0 >> 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, 1751364324, "C785@38222L90:FloatingAssistantOverlay.kt#qonjpd");
                        TextKt.Text--4IGK_g(mapObjectiveItem2.getSpawnTime(), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(2)), $composer2, 6);
                        TextKt.Text--4IGK_g(TranslatorKt.tr("Reaparición", $composer2, 6) + ": " + mapObjectiveItem2.getRespawnTime(), (Modifier) null, ColorKt.getTextMuted(), TextUnitKt.getSp(9.5d), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 0, 131058);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer2, 6);
                        TextKt.Text--4IGK_g(mapObjectiveItem2.getBuffDescription(), (Modifier) null, Color.copy-wmQWz5c$default(ColorKt.getTextPrimary(), 0.9f, 0.0f, 0.0f, 0.0f, 14, (Object) null), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(15), 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 6, 130034);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer, 54), $composer, 196614, 8);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final void OverlayItemsTabContent(String searchQuery, Function1<? super String, Unit> function1, ItemCategory selectedCategory, Function1<? super ItemCategory, Unit> function12, Composer $composer, int $changed) {
        ArrayList arrayList;
        Function1 function13;
        Composer $composer2 = $composer.startRestartGroup(-1321085753);
        ComposerKt.sourceInformation($composer2, "C(OverlayItemsTabContent)P(2,1,3)809@39231L518,820@39755L4171:FloatingAssistantOverlay.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(searchQuery) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(selectedCategory == null ? -1 : ((Enum) selectedCategory).ordinal()) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1321085753, $dirty, -1, "com.example.ui.components.OverlayItemsTabContent (FloatingAssistantOverlay.kt:808)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1746919789, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean z = (($dirty & 14) == 4) | (($dirty & 896) == 256);
            Object rememberedValue = $composer2.rememberedValue();
            if (z || rememberedValue == Composer.Companion.getEmpty()) {
                Iterable items = WildRiftRepository.INSTANCE.getItems();
                Collection arrayList2 = new ArrayList();
                for (Object obj : items) {
                    WildRiftItem wildRiftItem = (WildRiftItem) obj;
                    if ((selectedCategory == null || wildRiftItem.getCategory() == selectedCategory) && (StringsKt.isBlank(searchQuery) || StringsKt.contains(wildRiftItem.getName(), searchQuery, true) || StringsKt.contains(wildRiftItem.getPassive(), searchQuery, true) || StringsKt.contains(wildRiftItem.getStats(), searchQuery, true))) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = (List) arrayList2;
                $composer2.updateRememberedValue(arrayList3);
                arrayList = arrayList3;
            } else {
                arrayList = rememberedValue;
            }
            List filteredItems = (List) arrayList;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (6 >> 3)) | (112 & (6 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, fillMaxSize$default);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (6 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer composer = Updater.constructor-impl($composer2);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (6 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer2, -509661954, "C829@40329L242,822@39836L792,838@40638L40,844@40850L841,841@40720L971,864@41701L40,869@41881L2039,866@41751L2169:FloatingAssistantOverlay.kt#qonjpd");
            OutlinedTextFieldKt.OutlinedTextField(searchQuery, function1, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, false, (TextStyle) null, (Function2) null, ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$-1925595656$app(), ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$286820281$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10)), OutlinedTextFieldDefaults.INSTANCE.colors-0hiis_0(0L, 0L, 0L, 0L, ColorKt.getHextechSurface(), ColorKt.getHextechSurface(), 0L, 0L, 0L, 0L, (TextSelectionColors) null, ColorKt.getHextechCyan(), ColorKt.getHextechCardBorder(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 0, 0, 3072, 2147477455, 4095), $composer2, 113246592 | (14 & $dirty) | (112 & $dirty), 12582912, 0, 1965688);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
            FlowLayoutKt.FlowRow(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(4)), (Arrangement.Vertical) null, 0, 0, (FlowRowOverflow) null, ComposableLambdaKt.rememberComposableLambda(2022058444, true, (v2, v3, v4) -> {
                return OverlayItemsTabContent$lambda$150$lambda$146(r8, r9, v2, v3, v4);
            }, $composer2, 54), $composer2, 1572918, 60);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
            Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
            LazyListState lazyListState = null;
            PaddingValues paddingValues = null;
            boolean z2 = false;
            Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(6));
            Alignment.Horizontal horizontal = null;
            FlingBehavior flingBehavior = null;
            boolean z3 = false;
            ComposerKt.sourceInformationMarkerStart($composer2, 1369096008, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
            boolean changedInstance = $composer2.changedInstance(filteredItems);
            Object rememberedValue2 = $composer2.rememberedValue();
            if (changedInstance || rememberedValue2 == Composer.Companion.getEmpty()) {
                Function1 function14 = (v1) -> {
                    return OverlayItemsTabContent$lambda$150$lambda$149$lambda$148(r0, v1);
                };
                fillMaxSize$default2 = fillMaxSize$default2;
                lazyListState = null;
                paddingValues = null;
                z2 = false;
                vertical = vertical;
                horizontal = null;
                flingBehavior = null;
                z3 = false;
                $composer2.updateRememberedValue(function14);
                function13 = function14;
            } else {
                function13 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyColumn(fillMaxSize$default2, lazyListState, paddingValues, z2, vertical, horizontal, flingBehavior, z3, (Function1) function13, $composer2, 24582, 238);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope((v5, v6) -> {
                return OverlayItemsTabContent$lambda$151(r1, r2, r3, r4, r5, v5, v6);
            });
        }
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit OverlayItemsTabContent$lambda$150$lambda$146(ItemCategory $selectedCategory, Function1 $onCategoryChange, FlowRowScope $this$FlowRow, Composer $composer, int $changed) {
        Function0 function0;
        Intrinsics.checkNotNullParameter($this$FlowRow, "$this$FlowRow");
        ComposerKt.sourceInformation($composer, "C*851@41196L51,847@40975L692:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2022058444, $changed, -1, "com.example.ui.components.OverlayItemsTabContent.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:845)");
            }
            for (Enum r0 : ItemCategory.getEntries()) {
                boolean z = $selectedCategory == r0;
                Modifier modifier = BackgroundKt.background-bw27NRU$default(ClipKt.clip(Modifier.Companion, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(6))), z ? ColorKt.getHextechCyan() : ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null);
                boolean z2 = false;
                String str = null;
                Role role = null;
                ComposerKt.sourceInformationMarkerStart($composer, -1801598545, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changed = $composer.changed($onCategoryChange) | $composer.changed(z) | $composer.changed(r0.ordinal());
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    Function0 function02 = () -> {
                        return OverlayItemsTabContent$lambda$150$lambda$146$lambda$145$lambda$143$lambda$142(r0, r1, r2);
                    };
                    modifier = modifier;
                    z2 = false;
                    str = null;
                    role = null;
                    $composer.updateRememberedValue(function02);
                    function0 = function02;
                } else {
                    function0 = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifier2 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-XHw0xAI$default(modifier, z2, str, role, (Function0) function0, 7, (Object) null), Dp.constructor-impl(6), Dp.constructor-impl(3));
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor);
                } else {
                    $composer.useNode();
                }
                Composer composer = Updater.constructor-impl($composer);
                Updater.set-impl(composer, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i2 = 14 & (i >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                int i3 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, -263328067, "C855@41394L19,854@41357L292:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(TranslatorKt.tr(r0.getDisplayName(), $composer, 0), (Modifier) null, z ? ColorKt.getHextechDarkBg() : ColorKt.getTextMuted(), TextUnitKt.getSp(9.5d), (FontStyle) null, z ? FontWeight.Companion.getBold() : FontWeight.Companion.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit OverlayItemsTabContent$lambda$150$lambda$146$lambda$145$lambda$143$lambda$142(Function1 $onCategoryChange, boolean $isSelected, ItemCategory $cat) {
        $onCategoryChange.invoke($isSelected ? null : $cat);
        return Unit.INSTANCE;
    }

    private static final Unit OverlayItemsTabContent$lambda$150$lambda$149$lambda$148(final List $filteredItems, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayItemsTabContent$lambda$150$lambda$149$lambda$148$$inlined$items$default$1
            @Nullable
            public final Void invoke(WildRiftItem wildRiftItem) {
                return null;
            }

            /* renamed from: invoke  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object m1invoke(Object p1) {
                return invoke((WildRiftItem) p1);
            }
        };
        $this$LazyColumn.items($filteredItems.size(), (Function1) null, new Function1<Integer, Object>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayItemsTabContent$lambda$150$lambda$149$lambda$148$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            @Nullable
            public final Object invoke(int index) {
                return function1.invoke($filteredItems.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayItemsTabContent$lambda$150$lambda$149$lambda$148$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            @Composable
            public final void invoke(@NotNull LazyItemScope $this$items, int it, @Nullable Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (($dirty & 147) == 146 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                int i = 14 & $dirty;
                final WildRiftItem wildRiftItem = (WildRiftItem) $filteredItems.get(it);
                $composer.startReplaceGroup(166746680);
                ComposerKt.sourceInformation($composer, "C*874@42100L43,876@42258L1638,871@41942L1954:FloatingAssistantOverlay.kt#qonjpd");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), CardDefaults.INSTANCE.cardColors-ro_MJ88(ColorKt.getHextechSurface(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14), (CardElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1), ColorKt.getHextechCardBorder()), ComposableLambdaKt.rememberComposableLambda(-622959877, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayItemsTabContent$1$2$1$1$1
                    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                        invoke((ColumnScope) p1, (Composer) p2, ((Number) p3).intValue());
                        return Unit.INSTANCE;
                    }

                    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
                    @Composable
                    public final void invoke(ColumnScope $this$Card, Composer $composer2, int $changed2) {
                        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
                        ComposerKt.sourceInformation($composer2, "C877@42280L1598:FloatingAssistantOverlay.kt#qonjpd");
                        if (($changed2 & 17) == 16 && $composer2.getSkipping()) {
                            $composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-622959877, $changed2, -1, "com.example.ui.components.OverlayItemsTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:877)");
                        }
                        Modifier modifier = PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8));
                        Alignment.Vertical top = Alignment.Companion.getTop();
                        WildRiftItem wildRiftItem2 = wildRiftItem;
                        ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top, $composer2, (14 & (390 >> 3)) | (112 & (390 >> 3)));
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, modifier);
                        Function0 constructor = ComposeUiNode.Companion.getConstructor();
                        int i2 = 6 | (896 & ((112 & (390 << 3)) << 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor);
                        } else {
                            $composer2.useNode();
                        }
                        Composer composer = Updater.constructor-impl($composer2);
                        Updater.set-impl(composer, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                        int i3 = 14 & (i2 >> 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        int i4 = 6 | (112 & (390 >> 6));
                        RowScope rowScope = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1583787450, "C881@42450L379,889@42854L39,890@42918L938:FloatingAssistantOverlay.kt#qonjpd");
                        ChampionAvatarKt.AppAssetImage-FHprtrg(wildRiftItem2.getIconUrl(), wildRiftItem2.getName(), wildRiftItem2.getName(), SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(36)), ColorKt.getHextechGold(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(6)), $composer2, 3072, 0);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
                        Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
                        ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, weight$default);
                        Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                        int i5 = 6 | (896 & ((112 & (0 << 3)) << 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor2);
                        } else {
                            $composer2.useNode();
                        }
                        Composer composer2 = Updater.constructor-impl($composer2);
                        Updater.set-impl(composer2, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                        int i6 = 14 & (i5 >> 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                        int i7 = 6 | (112 & (0 >> 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, 1850277219, "C891@42987L556,899@43577L14,899@43572L59,900@43660L40,901@43734L16,901@43729L101:FloatingAssistantOverlay.kt#qonjpd");
                        Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                        Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                        Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer2, (14 & (438 >> 3)) | (112 & (438 >> 3)));
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default);
                        Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                        int i8 = 6 | (896 & ((112 & (438 << 3)) << 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor3);
                        } else {
                            $composer2.useNode();
                        }
                        Composer composer3 = Updater.constructor-impl($composer2);
                        Updater.set-impl(composer3, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                            composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                            composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                        }
                        Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                        int i9 = 14 & (i8 >> 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScope rowScope2 = RowScopeInstance.INSTANCE;
                        int i10 = 6 | (112 & (438 >> 6));
                        ComposerKt.sourceInformationMarkerStart($composer2, -846485626, "C896@43285L89,897@43432L9,897@43407L106:FloatingAssistantOverlay.kt#qonjpd");
                        TextKt.Text--4IGK_g(wildRiftItem2.getName(), (Modifier) null, ColorKt.getHextechGoldLight(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                        TextKt.Text--4IGK_g(wildRiftItem2.getGoldCost() + " " + TranslatorKt.tr("Oro", $composer2, 6), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        TextKt.Text--4IGK_g(TranslatorKt.tr(wildRiftItem2.getStats(), $composer2, 0), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 0, 131058);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(2)), $composer2, 6);
                        TextKt.Text--4IGK_g(TranslatorKt.tr(wildRiftItem2.getPassive(), $composer2, 0), (Modifier) null, Color.copy-wmQWz5c$default(ColorKt.getTextPrimary(), 0.85f, 0.0f, 0.0f, 0.0f, 14, (Object) null), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(13), 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 6, 130034);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer, 54), $composer, 196614, 8);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final void OverlayRunesTabContent(Champion lockedChampion, String searchQuery, Function1<? super String, Unit> function1, Function1<? super Champion, Unit> function12, Function0<Unit> function0, Composer $composer, int $changed) {
        ArrayList arrayList;
        Function1 function13;
        Composer $composer2 = $composer.startRestartGroup(47711630);
        ComposerKt.sourceInformation($composer2, "C(OverlayRunesTabContent)P(!1,4,2,3):FloatingAssistantOverlay.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(lockedChampion) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(searchQuery) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(47711630, $dirty, -1, "com.example.ui.components.OverlayRunesTabContent (FloatingAssistantOverlay.kt:920)");
            }
            if (lockedChampion == null) {
                $composer2.startReplaceGroup(-256645176);
                ComposerKt.sourceInformation($composer2, "922@44411L199,928@44620L3315");
                ComposerKt.sourceInformationMarkerStart($composer2, -1393754475, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean z = ($dirty & 112) == 32;
                Object rememberedValue = $composer2.rememberedValue();
                if (z || rememberedValue == Composer.Companion.getEmpty()) {
                    Iterable champions = WildRiftRepository.INSTANCE.getChampions();
                    Collection arrayList2 = new ArrayList();
                    for (Object obj : champions) {
                        if (StringsKt.isBlank(searchQuery) || StringsKt.contains(((Champion) obj).getName(), searchQuery, true)) {
                            arrayList2.add(obj);
                        }
                    }
                    ArrayList arrayList3 = (List) arrayList2;
                    $composer2.updateRememberedValue(arrayList3);
                    arrayList = arrayList3;
                } else {
                    arrayList = rememberedValue;
                }
                List matchingChampions = (List) arrayList;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer2, (14 & (390 >> 3)) | (112 & (390 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, fillMaxSize$default);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i = 6 | (896 & ((112 & (390 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                Composer composer = Updater.constructor-impl($composer2);
                Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i2 = 14 & (i >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                int i3 = 6 | (112 & (390 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1063029515, "C932@44762L697,950@45473L40,959@46020L262,952@45527L820,968@46361L40,973@46557L1368,970@46415L1510:FloatingAssistantOverlay.kt#qonjpd");
                Modifier modifier = PaddingKt.padding-3ABfNKs(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), Dp.constructor-impl(8));
                Alignment center = Alignment.Companion.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifier);
                Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                int i4 = 6 | (896 & ((112 & (48 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor2);
                } else {
                    $composer2.useNode();
                }
                Composer composer2 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer2, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                int i5 = 14 & (i4 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                int i6 = 6 | (112 & (48 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 621950184, "C942@45186L62,941@45153L292:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(TranslatorKt.tr("Selecciona un campeón para ver su página de runas óptima", $composer2, 6), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.box-impl(TextAlign.Companion.getCenter-e0LSkKk()), 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 130514);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
                OutlinedTextFieldKt.OutlinedTextField(searchQuery, function1, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, false, (TextStyle) null, (Function2) null, ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$1941870084$app(), ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$-1426342459$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10)), OutlinedTextFieldDefaults.INSTANCE.colors-0hiis_0(0L, 0L, 0L, 0L, ColorKt.getHextechSurface(), ColorKt.getHextechSurface(), 0L, 0L, 0L, 0L, (TextSelectionColors) null, ColorKt.getHextechCyan(), ColorKt.getHextechCardBorder(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 0, 0, 3072, 2147477455, 4095), $composer2, 113246592 | (14 & ($dirty >> 3)) | (112 & ($dirty >> 3)), 12582912, 0, 1965688);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
                Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                LazyListState lazyListState = null;
                PaddingValues paddingValues = null;
                boolean z2 = false;
                Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(4));
                Alignment.Horizontal horizontal = null;
                FlingBehavior flingBehavior = null;
                boolean z3 = false;
                ComposerKt.sourceInformationMarkerStart($composer2, 1558367573, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changedInstance = $composer2.changedInstance(matchingChampions) | (($dirty & 7168) == 2048);
                Object rememberedValue2 = $composer2.rememberedValue();
                if (changedInstance || rememberedValue2 == Composer.Companion.getEmpty()) {
                    Function1 function14 = (v2) -> {
                        return OverlayRunesTabContent$lambda$162$lambda$161$lambda$160(r0, r1, v2);
                    };
                    fillMaxSize$default2 = fillMaxSize$default2;
                    lazyListState = null;
                    paddingValues = null;
                    z2 = false;
                    vertical = vertical;
                    horizontal = null;
                    flingBehavior = null;
                    z3 = false;
                    $composer2.updateRememberedValue(function14);
                    function13 = function14;
                } else {
                    function13 = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LazyDslKt.LazyColumn(fillMaxSize$default2, lazyListState, paddingValues, z2, vertical, horizontal, flingBehavior, z3, (Function1) function13, $composer2, 24582, 238);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-253038357);
                ComposerKt.sourceInformation($composer2, "1002@48060L21,999@47957L5657");
                Modifier verticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, verticalScroll$default);
                Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                int i7 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
                } else {
                    $composer2.useNode();
                }
                Composer composer3 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer3, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                int i8 = 14 & (i7 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                int i9 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1917729886, "C1005@48147L1577,1041@49738L40,1047@49975L43,1049@50138L3466,1044@49828L3776:FloatingAssistantOverlay.kt#qonjpd");
                Modifier modifier2 = PaddingKt.padding-3ABfNKs(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getHextechCyan(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), Dp.constructor-impl(8));
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer2, (14 & (432 >> 3)) | (112 & (432 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifier2);
                Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
                int i10 = 6 | (896 & ((112 & (432 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor4);
                } else {
                    $composer2.useNode();
                }
                Composer composer4 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer4, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer4, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer4.getInserting() || !Intrinsics.areEqual(composer4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composer4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composer4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.set-impl(composer4, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
                int i11 = 14 & (i10 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope = RowScopeInstance.INSTANCE;
                int i12 = 6 | (112 & (432 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 813574745, "C1015@48596L811,1033@49425L285:FloatingAssistantOverlay.kt#qonjpd");
                Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier3 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer2, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap5 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer2, modifier3);
                Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
                int i13 = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor5);
                } else {
                    $composer2.useNode();
                }
                Composer composer5 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer5, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer5.getInserting() || !Intrinsics.areEqual(composer5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    composer5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                    composer5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                }
                Updater.set-impl(composer5, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
                int i14 = 14 & (i13 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                int i15 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 890454632, "C1016@48670L55,1017@48746L39,1018@48806L583:FloatingAssistantOverlay.kt#qonjpd");
                ChampionAvatarKt.ChampionAvatar-DzVHIIc(lockedChampion, Dp.constructor-impl(36), false, (Modifier) null, $composer2, 48 | (14 & $dirty), 12);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Modifier modifier4 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap6 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer2, modifier4);
                Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
                int i16 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor6);
                } else {
                    $composer2.useNode();
                }
                Composer composer6 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer6, columnMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer6, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer6.getInserting() || !Intrinsics.areEqual(composer6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    composer6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                    composer6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                }
                Updater.set-impl(composer6, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
                int i17 = 14 & (i16 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope3 = ColumnScopeInstance.INSTANCE;
                int i18 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1166536125, "C1020@48907L12,1019@48839L262,1026@49170L42,1026@49218L21,1025@49126L241:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(lockedChampion.getName() + " (" + TranslatorKt.tr("Fijado", $composer2, 6) + ")", (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                TextKt.Text--4IGK_g(TranslatorKt.tr(lockedChampion.getPrimaryRole().getDisplayName(), $composer2, 0) + " • " + TranslatorKt.tr("Página de Runas", $composer2, 6), (Modifier) null, ColorKt.getHextechGoldLight(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                IconButtonKt.IconButton(function0, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(24)), false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$-1337130989$app(), $composer2, 196656 | (14 & ($dirty >> 12)), 28);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10)), CardDefaults.INSTANCE.cardColors-ro_MJ88(ColorKt.getHextechSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), (CardElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1), Color.copy-wmQWz5c$default(ColorKt.getHextechCyan(), 0.6f, 0.0f, 0.0f, 0.0f, 14, (Object) null)), ComposableLambdaKt.rememberComposableLambda(1314627458, true, (v1, v2, v3) -> {
                    return OverlayRunesTabContent$lambda$174$lambda$173(r7, v1, v2, v3);
                }, $composer2, 54), $composer2, 196614, 8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope((v6, v7) -> {
                return OverlayRunesTabContent$lambda$175(r1, r2, r3, r4, r5, r6, v6, v7);
            });
        }
    }

    private static final Unit OverlayRunesTabContent$lambda$162$lambda$161$lambda$160(final List $matchingChampions, final Function1 $onSelectChampion, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayRunesTabContent$lambda$162$lambda$161$lambda$160$$inlined$items$default$1
            @Nullable
            public final Void invoke(Champion champion) {
                return null;
            }

            /* renamed from: invoke  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object m5invoke(Object p1) {
                return invoke((Champion) p1);
            }
        };
        $this$LazyColumn.items($matchingChampions.size(), (Function1) null, new Function1<Integer, Object>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayRunesTabContent$lambda$162$lambda$161$lambda$160$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            @Nullable
            public final Object invoke(int index) {
                return function1.invoke($matchingChampions.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayRunesTabContent$lambda$162$lambda$161$lambda$160$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            @Composable
            public final void invoke(@NotNull LazyItemScope $this$items, int it, @Nullable Composer $composer, int $changed) {
                Function0<Unit> function0;
                ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (($dirty & 147) == 146 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                int i = 14 & $dirty;
                final Champion champion = (Champion) $matchingChampions.get(it);
                $composer.startReplaceGroup(-981246841);
                ComposerKt.sourceInformation($composer, "C*980@46879L27,975@46631L1262:FloatingAssistantOverlay.kt#qonjpd");
                Modifier modifier = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null);
                boolean z = false;
                String str = null;
                Role role = null;
                ComposerKt.sourceInformationMarkerStart($composer, -31646423, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changed = $composer.changed($onSelectChampion) | $composer.changedInstance(champion);
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    final Function1 function12 = $onSelectChampion;
                    Function0<Unit> function02 = new Function0<Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlayRunesTabContent$1$2$1$1$1$1
                        public final void invoke() {
                            function12.invoke(champion);
                        }

                        /* renamed from: invoke  reason: collision with other method in class */
                        public /* bridge */ /* synthetic */ Object m8invoke() {
                            invoke();
                            return Unit.INSTANCE;
                        }
                    };
                    modifier = modifier;
                    z = false;
                    str = null;
                    role = null;
                    $composer.updateRememberedValue(function02);
                    function0 = function02;
                } else {
                    function0 = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifier2 = PaddingKt.padding-3ABfNKs(ClickableKt.clickable-XHw0xAI$default(modifier, z, str, role, (Function0) function0, 7, (Object) null), Dp.constructor-impl(6));
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer, (14 & (432 >> 3)) | (112 & (432 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i2 = 6 | (896 & ((112 & (432 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor);
                } else {
                    $composer.useNode();
                }
                Composer composer = Updater.constructor-impl($composer);
                Updater.set-impl(composer, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i3 = 14 & (i2 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope = RowScopeInstance.INSTANCE;
                int i4 = 6 | (112 & (432 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, -1933014662, "C985@47144L588,993@47757L114:FloatingAssistantOverlay.kt#qonjpd");
                Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier3 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                int i5 = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor2);
                } else {
                    $composer.useNode();
                }
                Composer composer2 = Updater.constructor-impl($composer);
                Updater.set-impl(composer2, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                int i6 = 14 & (i5 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                int i7 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, -906990803, "C986@47226L69,987@47324L39,988@47392L314:FloatingAssistantOverlay.kt#qonjpd");
                ChampionAvatarKt.ChampionAvatar-DzVHIIc(champion, Dp.constructor-impl(32), false, (Modifier) null, $composer, 432 | (14 & (i >> 3)), 8);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Modifier modifier4 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier4);
                Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                int i8 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor3);
                } else {
                    $composer.useNode();
                }
                Composer composer3 = Updater.constructor-impl($composer);
                Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                int i9 = 14 & (i8 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                int i10 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, 307286980, "C989@47433L85,990@47559L31,990@47596L11,990@47551L125:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(champion.getName(), (Modifier) null, ColorKt.getTextPrimary(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
                TextKt.Text--4IGK_g(TranslatorKt.tr(champion.getPrimaryRole().getShortName(), $composer, 0) + " • " + TranslatorKt.tr("Runas", $composer, 6) + ": " + champion.getRecommendedRunes(), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconKt.Icon-ww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Seleccionar", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), ColorKt.getHextechCyan(), $composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit OverlayRunesTabContent$lambda$174$lambda$173(Champion $lockedChampion, ColumnScope $this$Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C1050@50156L3434:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1314627458, $changed, -1, "com.example.ui.components.OverlayRunesTabContent.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:1050)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (6 >> 3)) | (112 & (6 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (6 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (6 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -136041792, "C1052@50266L28,1051@50221L236,1057@50478L40,1058@50539L237:FloatingAssistantOverlay.kt#qonjpd");
            TextKt.Text--4IGK_g("�� " + TranslatorKt.tr("Runa Clave Recomendada", $composer, 6), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
            TextKt.Text--4IGK_g($lockedChampion.getRecommendedRunes(), (Modifier) null, ColorKt.getHextechGoldLight(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getBlack(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            if (!StringsKt.isBlank($lockedChampion.getRuneTreeDetails())) {
                $composer.startReplaceGroup(-135441168);
                ComposerKt.sourceInformation($composer, "");
                Iterable<String> split$default = StringsKt.split$default(new Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+:\\s*").replace($lockedChampion.getRuneTreeDetails(), ""), new String[]{"•"}, false, 0, 6, (Object) null);
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
                for (String str : split$default) {
                    arrayList.add(StringsKt.trim(str).toString());
                }
                Iterable iterable = (List) arrayList;
                Collection arrayList2 = new ArrayList();
                for (Object obj : iterable) {
                    if (((String) obj).length() > 0) {
                        arrayList2.add(obj);
                    }
                }
                List list = (List) arrayList2;
                if (!(!list.isEmpty())) {
                    $composer.startReplaceGroup(-133231550);
                    ComposerKt.sourceInformation($composer, "1100@53176L40,1101@53245L279");
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
                    TextKt.Text--4IGK_g($lockedChampion.getRuneTreeDetails(), (Modifier) null, Color.copy-wmQWz5c$default(ColorKt.getTextPrimary(), 0.9f, 0.0f, 0.0f, 0.0f, 14, (Object) null), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(15), 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 6, 130034);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-135093224);
                    ComposerKt.sourceInformation($composer, "1072@51252L40,1077@51590L1524,1074@51386L1728");
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer, 6);
                    FlowLayoutKt.FlowRow((Modifier) null, Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8)), Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(6)), 0, 0, (FlowRowOverflow) null, ComposableLambdaKt.rememberComposableLambda(638936657, true, (v1, v2, v3) -> {
                        return OverlayRunesTabContent$lambda$174$lambda$173$lambda$172$lambda$171(r8, v1, v2, v3);
                    }, $composer, 54), $composer, 1573296, 57);
                    $composer.endReplaceGroup();
                }
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-185965898);
                $composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit OverlayRunesTabContent$lambda$174$lambda$173$lambda$172$lambda$171(List $parsedRunes, FlowRowScope $this$FlowRow, Composer $composer, int $changed) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter($this$FlowRow, "$this$FlowRow");
        ComposerKt.sourceInformation($composer, "C*1081@51929L1121:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(638936657, $changed, -1, "com.example.ui.components.OverlayRunesTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:1078)");
            }
            Iterator it = $parsedRunes.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Iterator it2 = WildRiftSpellsAndRunes.INSTANCE.getRunes().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    Object next = it2.next();
                    RuneItem runeItem = (RuneItem) next;
                    if (StringsKt.equals(runeItem.getName(), str, true) || StringsKt.contains$default(str, runeItem.getName(), false, 2, (Object) null)) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        obj = next;
                        break;
                    }
                }
                RuneItem runeItem2 = (RuneItem) obj;
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor);
                } else {
                    $composer.useNode();
                }
                Composer composer = Updater.constructor-impl($composer);
                Updater.set-impl(composer, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i2 = 14 & (i >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope = RowScopeInstance.INSTANCE;
                int i3 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, -1258774783, "C1095@52943L69:FloatingAssistantOverlay.kt#qonjpd");
                if (runeItem2 != null) {
                    $composer.startReplaceGroup(-1258763841);
                    ComposerKt.sourceInformation($composer, "1083@52092L434,1090@52571L39");
                    ChampionAvatarKt.AppAssetImage-FHprtrg(runeItem2.getIconUrl(), runeItem2.getName(), "", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), 0L, RoundedCornerShapeKt.getCircleShape(), $composer, 3456, 16);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-1258167959);
                    ComposerKt.sourceInformation($composer, "1092@52704L72,1093@52821L39");
                    BoxKt.Box(BackgroundKt.background-bw27NRU(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), ColorKt.getHextechCyan(), RoundedCornerShapeKt.getCircleShape()), $composer, 0);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
                    $composer.endReplaceGroup();
                }
                TextKt.Text--4IGK_g(str, (Modifier) null, Color.copy-wmQWz5c$default(ColorKt.getTextPrimary(), 0.9f, 0.0f, 0.0f, 0.0f, 14, (Object) null), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final void OverlaySpellsTabContent(Champion lockedChampion, String searchQuery, Function1<? super String, Unit> function1, Function1<? super Champion, Unit> function12, Function0<Unit> function0, Composer $composer, int $changed) {
        ArrayList arrayList;
        Function1 function13;
        Composer $composer2 = $composer.startRestartGroup(1494760868);
        ComposerKt.sourceInformation($composer2, "C(OverlaySpellsTabContent)P(!1,4,2,3):FloatingAssistantOverlay.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(lockedChampion) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(searchQuery) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1494760868, $dirty, -1, "com.example.ui.components.OverlaySpellsTabContent (FloatingAssistantOverlay.kt:1125)");
            }
            if (lockedChampion == null) {
                $composer2.startReplaceGroup(200455990);
                ComposerKt.sourceInformation($composer2, "1127@54125L199,1133@54334L3343");
                ComposerKt.sourceInformationMarkerStart($composer2, 1946126667, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean z = ($dirty & 112) == 32;
                Object rememberedValue = $composer2.rememberedValue();
                if (z || rememberedValue == Composer.Companion.getEmpty()) {
                    Iterable champions = WildRiftRepository.INSTANCE.getChampions();
                    Collection arrayList2 = new ArrayList();
                    for (Object obj : champions) {
                        if (StringsKt.isBlank(searchQuery) || StringsKt.contains(((Champion) obj).getName(), searchQuery, true)) {
                            arrayList2.add(obj);
                        }
                    }
                    ArrayList arrayList3 = (List) arrayList2;
                    $composer2.updateRememberedValue(arrayList3);
                    arrayList = arrayList3;
                } else {
                    arrayList = rememberedValue;
                }
                List matchingChampions = (List) arrayList;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer2, (14 & (390 >> 3)) | (112 & (390 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, fillMaxSize$default);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i = 6 | (896 & ((112 & (390 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                Composer composer = Updater.constructor-impl($composer2);
                Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i2 = 14 & (i >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                int i3 = 6 | (112 & (390 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -1831846761, "C1137@54476L710,1155@55200L40,1164@55740L262,1157@55254L813,1173@56081L40,1178@56277L1390,1175@56135L1532:FloatingAssistantOverlay.kt#qonjpd");
                Modifier modifier = PaddingKt.padding-3ABfNKs(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), Color.copy-wmQWz5c$default(ColorKt.getHextechGold(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), Dp.constructor-impl(8));
                Alignment center = Alignment.Companion.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifier);
                Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                int i4 = 6 | (896 & ((112 & (48 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor2);
                } else {
                    $composer2.useNode();
                }
                Composer composer2 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer2, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                int i5 = 14 & (i4 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                int i6 = 6 | (112 & (48 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1677113687, "C1147@54900L75,1146@54867L305:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(TranslatorKt.tr("Selecciona un campeón para ver sus hechizos de invocador recomendados", $composer2, 6), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.box-impl(TextAlign.Companion.getCenter-e0LSkKk()), 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 130514);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
                OutlinedTextFieldKt.OutlinedTextField(searchQuery, function1, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, false, (TextStyle) null, (Function2) null, ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$84130798$app(), ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$-1251242931$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10)), OutlinedTextFieldDefaults.INSTANCE.colors-0hiis_0(0L, 0L, 0L, 0L, ColorKt.getHextechSurface(), ColorKt.getHextechSurface(), 0L, 0L, 0L, 0L, (TextSelectionColors) null, ColorKt.getHextechGold(), ColorKt.getHextechCardBorder(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 0, 0, 3072, 2147477455, 4095), $composer2, 113246592 | (14 & ($dirty >> 3)) | (112 & ($dirty >> 3)), 12582912, 0, 1965688);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer2, 6);
                Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                LazyListState lazyListState = null;
                PaddingValues paddingValues = null;
                boolean z2 = false;
                Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(4));
                Alignment.Horizontal horizontal = null;
                FlingBehavior flingBehavior = null;
                boolean z3 = false;
                ComposerKt.sourceInformationMarkerStart($composer2, -1028867325, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changedInstance = $composer2.changedInstance(matchingChampions) | (($dirty & 7168) == 2048);
                Object rememberedValue2 = $composer2.rememberedValue();
                if (changedInstance || rememberedValue2 == Composer.Companion.getEmpty()) {
                    Function1 function14 = (v2) -> {
                        return OverlaySpellsTabContent$lambda$186$lambda$185$lambda$184(r0, r1, v2);
                    };
                    fillMaxSize$default2 = fillMaxSize$default2;
                    lazyListState = null;
                    paddingValues = null;
                    z2 = false;
                    vertical = vertical;
                    horizontal = null;
                    flingBehavior = null;
                    z3 = false;
                    $composer2.updateRememberedValue(function14);
                    function13 = function14;
                } else {
                    function13 = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LazyDslKt.LazyColumn(fillMaxSize$default2, lazyListState, paddingValues, z2, vertical, horizontal, flingBehavior, z3, (Function1) function13, $composer2, 24582, 238);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(204054687);
                ComposerKt.sourceInformation($composer2, "1207@57802L21,1204@57699L4527");
                Modifier verticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, verticalScroll$default);
                Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                int i7 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
                } else {
                    $composer2.useNode();
                }
                Composer composer3 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer3, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                int i8 = 14 & (i7 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                int i9 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -1109298754, "C1210@57889L1588,1246@59491L40,1252@59745L43,1254@59908L2308,1249@59598L2618:FloatingAssistantOverlay.kt#qonjpd");
                Modifier modifier2 = PaddingKt.padding-3ABfNKs(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getHextechGold(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10))), Dp.constructor-impl(8));
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer2, (14 & (432 >> 3)) | (112 & (432 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifier2);
                Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
                int i10 = 6 | (896 & ((112 & (432 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor4);
                } else {
                    $composer2.useNode();
                }
                Composer composer4 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer4, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer4, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer4.getInserting() || !Intrinsics.areEqual(composer4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composer4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composer4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.set-impl(composer4, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
                int i11 = 14 & (i10 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope = RowScopeInstance.INSTANCE;
                int i12 = 6 | (112 & (432 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -973224036, "C1220@58338L822,1238@59178L285:FloatingAssistantOverlay.kt#qonjpd");
                Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier3 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer2, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap5 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer2, modifier3);
                Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
                int i13 = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor5);
                } else {
                    $composer2.useNode();
                }
                Composer composer5 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer5, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer5.getInserting() || !Intrinsics.areEqual(composer5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    composer5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                    composer5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                }
                Updater.set-impl(composer5, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
                int i14 = 14 & (i13 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                int i15 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1410300771, "C1221@58412L55,1222@58488L39,1223@58548L594:FloatingAssistantOverlay.kt#qonjpd");
                ChampionAvatarKt.ChampionAvatar-DzVHIIc(lockedChampion, Dp.constructor-impl(36), false, (Modifier) null, $composer2, 48 | (14 & $dirty), 12);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Modifier modifier4 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer2, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap6 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer2, modifier4);
                Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
                int i16 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor6);
                } else {
                    $composer2.useNode();
                }
                Composer composer6 = Updater.constructor-impl($composer2);
                Updater.set-impl(composer6, columnMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer6, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer6.getInserting() || !Intrinsics.areEqual(composer6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    composer6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                    composer6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                }
                Updater.set-impl(composer6, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
                int i17 = 14 & (i16 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope3 = ColumnScopeInstance.INSTANCE;
                int i18 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer2, 1378951052, "C1225@58649L12,1224@58581L262,1231@58912L42,1231@58960L37,1230@58868L252:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(lockedChampion.getName() + " (" + TranslatorKt.tr("Fijado", $composer2, 6) + ")", (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 199680, 0, 131026);
                TextKt.Text--4IGK_g(TranslatorKt.tr(lockedChampion.getPrimaryRole().getDisplayName(), $composer2, 0) + " • " + TranslatorKt.tr("Hechizos & Orden de Habilidades", $composer2, 6), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer2, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                IconButtonKt.IconButton(function0, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(24)), false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons.FloatingAssistantOverlayKt.INSTANCE.getLambda$1514312639$app(), $composer2, 196656 | (14 & ($dirty >> 12)), 28);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer2, 6);
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10)), CardDefaults.INSTANCE.cardColors-ro_MJ88(ColorKt.getHextechSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), (CardElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1), Color.copy-wmQWz5c$default(ColorKt.getHextechGold(), 0.6f, 0.0f, 0.0f, 0.0f, 14, (Object) null)), ComposableLambdaKt.rememberComposableLambda(2114445872, true, (v1, v2, v3) -> {
                    return OverlaySpellsTabContent$lambda$195$lambda$194(r7, v1, v2, v3);
                }, $composer2, 54), $composer2, 196614, 8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope((v6, v7) -> {
                return OverlaySpellsTabContent$lambda$196(r1, r2, r3, r4, r5, r6, v6, v7);
            });
        }
    }

    private static final Unit OverlaySpellsTabContent$lambda$186$lambda$185$lambda$184(final List $matchingChampions, final Function1 $onSelectChampion, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlaySpellsTabContent$lambda$186$lambda$185$lambda$184$$inlined$items$default$1
            @Nullable
            public final Void invoke(Champion champion) {
                return null;
            }

            /* renamed from: invoke  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object m7invoke(Object p1) {
                return invoke((Champion) p1);
            }
        };
        $this$LazyColumn.items($matchingChampions.size(), (Function1) null, new Function1<Integer, Object>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlaySpellsTabContent$lambda$186$lambda$185$lambda$184$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            @Nullable
            public final Object invoke(int index) {
                return function1.invoke($matchingChampions.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlaySpellsTabContent$lambda$186$lambda$185$lambda$184$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            @Composable
            public final void invoke(@NotNull LazyItemScope $this$items, int it, @Nullable Composer $composer, int $changed) {
                Function0<Unit> function0;
                ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (($dirty & 147) == 146 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                int i = 14 & $dirty;
                final Champion champion = (Champion) $matchingChampions.get(it);
                $composer.startReplaceGroup(-778642533);
                ComposerKt.sourceInformation($composer, "C*1185@56599L27,1180@56351L1284:FloatingAssistantOverlay.kt#qonjpd");
                Modifier modifier = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8))), ColorKt.getHextechSurface(), (Shape) null, 2, (Object) null);
                boolean z = false;
                String str = null;
                Role role = null;
                ComposerKt.sourceInformationMarkerStart($composer, 1221815167, "CC(remember):FloatingAssistantOverlay.kt#9igjgp");
                boolean changed = $composer.changed($onSelectChampion) | $composer.changedInstance(champion);
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    final Function1 function12 = $onSelectChampion;
                    Function0<Unit> function02 = new Function0<Unit>() { // from class: com.example.ui.components.FloatingAssistantOverlayKt$OverlaySpellsTabContent$1$2$1$1$1$1
                        public final void invoke() {
                            function12.invoke(champion);
                        }

                        /* renamed from: invoke  reason: collision with other method in class */
                        public /* bridge */ /* synthetic */ Object m9invoke() {
                            invoke();
                            return Unit.INSTANCE;
                        }
                    };
                    modifier = modifier;
                    z = false;
                    str = null;
                    role = null;
                    $composer.updateRememberedValue(function02);
                    function0 = function02;
                } else {
                    function0 = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifier2 = PaddingKt.padding-3ABfNKs(ClickableKt.clickable-XHw0xAI$default(modifier, z, str, role, (Function0) function0, 7, (Object) null), Dp.constructor-impl(6));
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), centerVertically, $composer, (14 & (432 >> 3)) | (112 & (432 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i2 = 6 | (896 & ((112 & (432 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor);
                } else {
                    $composer.useNode();
                }
                Composer composer = Updater.constructor-impl($composer);
                Updater.set-impl(composer, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i3 = 14 & (i2 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope = RowScopeInstance.INSTANCE;
                int i4 = 6 | (112 & (432 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, -218027562, "C1190@56864L610,1198@57499L114:FloatingAssistantOverlay.kt#qonjpd");
                Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier3 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, (14 & (384 >> 3)) | (112 & (384 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                int i5 = 6 | (896 & ((112 & (384 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor2);
                } else {
                    $composer.useNode();
                }
                Composer composer2 = Updater.constructor-impl($composer);
                Updater.set-impl(composer2, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                int i6 = 14 & (i5 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                int i7 = 6 | (112 & (384 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, 1524051665, "C1191@56946L69,1192@57044L39,1193@57112L336:FloatingAssistantOverlay.kt#qonjpd");
                ChampionAvatarKt.ChampionAvatar-DzVHIIc(champion, Dp.constructor-impl(32), false, (Modifier) null, $composer, 432 | (14 & (i >> 3)), 8);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Modifier modifier4 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (0 >> 3)) | (112 & (0 >> 3)));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier4);
                Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                int i8 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor3);
                } else {
                    $composer.useNode();
                }
                Composer composer3 = Updater.constructor-impl($composer);
                Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                int i9 = 14 & (i8 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                int i10 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, 512058644, "C1194@57153L85,1195@57279L31,1195@57316L14,1195@57271L147:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(champion.getName(), (Modifier) null, ColorKt.getTextPrimary(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
                TextKt.Text--4IGK_g(TranslatorKt.tr(champion.getPrimaryRole().getShortName(), $composer, 0) + " • " + TranslatorKt.tr("Hechizos", $composer, 6) + ": " + CollectionsKt.joinToString$default(champion.getRecommendedSpells(), "+", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconKt.Icon-ww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Seleccionar", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), ColorKt.getHextechGold(), $composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final Unit OverlaySpellsTabContent$lambda$195$lambda$194(Champion $lockedChampion, ColumnScope $this$Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C1255@59926L2276:FloatingAssistantOverlay.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2114445872, $changed, -1, "com.example.ui.components.OverlaySpellsTabContent.<anonymous>.<anonymous> (FloatingAssistantOverlay.kt:1255)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), $composer, (14 & (6 >> 3)) | (112 & (6 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = 6 | (896 & ((112 & (6 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = 14 & (i >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = 6 | (112 & (6 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -348801300, "C1257@60035L40,1256@59991L247,1262@60259L40,1263@60320L1063,1285@61405L41,1286@61467L64,1287@61552L40,1290@61659L30,1289@61614L238,1295@61873L40,1297@61971L12,1296@61934L250:FloatingAssistantOverlay.kt#qonjpd");
            TextKt.Text--4IGK_g("⚡ " + TranslatorKt.tr("Hechizos de Invocador Recomendados", $composer, 6), (Modifier) null, ColorKt.getHextechGold(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
            Arrangement.Horizontal horizontal = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(horizontal, centerVertically, $composer, (14 & (432 >> 3)) | (112 & (432 >> 3)));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i4 = 6 | (896 & ((112 & (432 << 3)) << 6));
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor2);
            } else {
                $composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl($composer);
            Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer2.getInserting() || !Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.set-impl(composer2, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i5 = 14 & (i4 >> 6);
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i6 = 6 | (112 & (432 >> 6));
            ComposerKt.sourceInformationMarkerStart($composer, -974392624, "C:FloatingAssistantOverlay.kt#qonjpd");
            $composer.startReplaceGroup(-862714990);
            ComposerKt.sourceInformation($composer, "*1268@60600L735");
            for (String str : $lockedChampion.getRecommendedSpells()) {
                Modifier modifier3 = PaddingKt.padding-VpY3zN4(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(Modifier.Companion, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(6))), ColorKt.getHextechSurfaceVariant(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getHextechGold(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(6))), Dp.constructor-impl(10), Dp.constructor-impl(5));
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                int i7 = 6 | (896 & ((112 & (0 << 3)) << 6));
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor3);
                } else {
                    $composer.useNode();
                }
                Composer composer3 = Updater.constructor-impl($composer);
                Updater.set-impl(composer3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer3.getInserting() || !Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composer3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.set-impl(composer3, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                int i8 = 14 & (i7 >> 6);
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                int i9 = 6 | (112 & (0 >> 6));
                ComposerKt.sourceInformationMarkerStart($composer, 1663094621, "C1276@61080L9,1275@61031L274:FloatingAssistantOverlay.kt#qonjpd");
                TextKt.Text--4IGK_g(TranslatorKt.tr(str, $composer, 0), (Modifier) null, ColorKt.getHextechGoldLight(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), $composer, 6);
            DividerKt.HorizontalDivider-9IZ8Weo((Modifier) null, Dp.constructor-impl((float) 0.5d), ColorKt.getHextechCardBorder(), $composer, 48, 1);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), $composer, 6);
            TextKt.Text--4IGK_g("�� " + TranslatorKt.tr("Prioridad de Habilidades", $composer, 6), (Modifier) null, ColorKt.getHextechCyan(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
            TextKt.Text--4IGK_g(TranslatorKt.tr("Maxeo:", $composer, 6) + " " + $lockedChampion.getSkillOrder(), (Modifier) null, ColorKt.getTextPrimary(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
