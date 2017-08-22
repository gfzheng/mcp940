package net.minecraft.advancements;

import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdvancementList
{
    private static final Logger field_192091_a = LogManager.getLogger();
    private final Map<ResourceLocation, Advancement> field_192092_b = Maps.<ResourceLocation, Advancement>newHashMap();
    private final Set<Advancement> field_192093_c = Sets.<Advancement>newLinkedHashSet();
    private final Set<Advancement> field_192094_d = Sets.<Advancement>newLinkedHashSet();
    private AdvancementList.Listener field_192095_e;

    public void func_192083_a(Map<ResourceLocation, Advancement.Builder> p_192083_1_)
    {
        Function<ResourceLocation, Advancement> function = Functions.<ResourceLocation, Advancement>forMap(this.field_192092_b, null);
        label42:

        while (!p_192083_1_.isEmpty())
        {
            boolean flag = false;
            Iterator<Entry<ResourceLocation, Advancement.Builder>> iterator = p_192083_1_.entrySet().iterator();

            while (iterator.hasNext())
            {
                Entry<ResourceLocation, Advancement.Builder> entry = (Entry)iterator.next();
                ResourceLocation resourcelocation = entry.getKey();
                Advancement.Builder advancement$builder = entry.getValue();

                if (advancement$builder.func_192058_a(function))
                {
                    Advancement advancement = advancement$builder.func_192056_a(resourcelocation);
                    this.field_192092_b.put(resourcelocation, advancement);
                    flag = true;
                    iterator.remove();

                    if (advancement.func_192070_b() == null)
                    {
                        this.field_192093_c.add(advancement);

                        if (this.field_192095_e != null)
                        {
                            this.field_192095_e.func_191931_a(advancement);
                        }
                    }
                    else
                    {
                        this.field_192094_d.add(advancement);

                        if (this.field_192095_e != null)
                        {
                            this.field_192095_e.func_191932_c(advancement);
                        }
                    }
                }
            }

            if (!flag)
            {
                iterator = p_192083_1_.entrySet().iterator();

                while (true)
                {
                    if (!iterator.hasNext())
                    {
                        break label42;
                    }

                    Entry<ResourceLocation, Advancement.Builder> entry1 = (Entry)iterator.next();
                    field_192091_a.error("Couldn't load advancement " + entry1.getKey() + ": " + entry1.getValue());
                }
            }
        }

        field_192091_a.info("Loaded " + this.field_192092_b.size() + " advancements");
    }

    public void func_192087_a()
    {
        this.field_192092_b.clear();
        this.field_192093_c.clear();
        this.field_192094_d.clear();

        if (this.field_192095_e != null)
        {
            this.field_192095_e.func_191930_a();
        }
    }

    public Iterable<Advancement> func_192088_b()
    {
        return this.field_192093_c;
    }

    public Iterable<Advancement> func_192089_c()
    {
        return this.field_192092_b.values();
    }

    @Nullable
    public Advancement func_192084_a(ResourceLocation p_192084_1_)
    {
        return this.field_192092_b.get(p_192084_1_);
    }

    public interface Listener
    {
        void func_191931_a(Advancement p_191931_1_);

        void func_191932_c(Advancement p_191932_1_);

        void func_191930_a();
    }
}
