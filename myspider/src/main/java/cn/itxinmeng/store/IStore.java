package cn.itxinmeng.store;

/**心梦
 * 存储接口
 * @param <T>
 */
public interface IStore<T> {
    void store(T t);
}
