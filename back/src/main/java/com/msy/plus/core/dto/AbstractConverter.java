package com.msy.plus.core.dto;

import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.ParameterizedType;

/**
 * DTO -> DO 抽象转换器
 *
 * @author MoShuying
 * @date 2018/11/28
 */
public abstract class AbstractConverter<DTO, DO> extends Converter<DTO, DO> {
  private final Class<DO> doClass;
  private final DTO theDTO = this.setDTO();
  private DO theDO;

  public AbstractConverter() {
    final ParameterizedType parameterizedType =
        (ParameterizedType) this.getClass().getGenericSuperclass();
    this.doClass = (Class<DO>) parameterizedType.getActualTypeArguments()[1];
  }

  /**
   * 设置 DTO
   *
   * @return DTO
   */
  protected abstract DTO setDTO();

  @Override
  @ParametersAreNonnullByDefault
  public DO doForward(final DTO theDTO) {
    BeanUtils.copyProperties(theDTO, this.theDO);
    return this.theDO;
  }

  @Override
  @ParametersAreNonnullByDefault
  public DTO doBackward(final DO theDO) {
    BeanUtils.copyProperties(theDO, this.theDTO);
    return this.theDTO;
  }

  public DO convertToDO() {
    try {
      this.theDO = this.doClass.getDeclaredConstructor().newInstance();
      return this.convert(this.theDTO);
    } catch (final Exception ignored) {
      return null;
    }
  }

  public DTO convertFor(final DO aDO) {
    return this.reverse().convert(aDO);
  }
}
