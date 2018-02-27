// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: p_user_info.proto

package com.x_keam.protobuff.model;

public final class PUserInfoOuterClass {
  private PUserInfoOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PUserInfoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.x_keam.protobuff.model.PUserInfo)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 response_code = 1;</code>
     */
    int getResponseCode();

    /**
     * <code>string response_message = 2;</code>
     */
    java.lang.String getResponseMessage();
    /**
     * <code>string response_message = 2;</code>
     */
    com.google.protobuf.ByteString
        getResponseMessageBytes();

    /**
     * <code>uint64 id = 3;</code>
     */
    long getId();

    /**
     * <code>string name = 4;</code>
     */
    java.lang.String getName();
    /**
     * <code>string name = 4;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>string avatar = 5;</code>
     */
    java.lang.String getAvatar();
    /**
     * <code>string avatar = 5;</code>
     */
    com.google.protobuf.ByteString
        getAvatarBytes();
  }
  /**
   * Protobuf type {@code com.x_keam.protobuff.model.PUserInfo}
   */
  public  static final class PUserInfo extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.x_keam.protobuff.model.PUserInfo)
      PUserInfoOrBuilder {
    // Use PUserInfo.newBuilder() to construct.
    private PUserInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PUserInfo() {
      responseCode_ = 0;
      responseMessage_ = "";
      id_ = 0L;
      name_ = "";
      avatar_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private PUserInfo(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              responseCode_ = input.readInt32();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              responseMessage_ = s;
              break;
            }
            case 24: {

              id_ = input.readUInt64();
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              avatar_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.x_keam.protobuff.model.PUserInfoOuterClass.internal_static_com_x_keam_protobuff_model_PUserInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.x_keam.protobuff.model.PUserInfoOuterClass.internal_static_com_x_keam_protobuff_model_PUserInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.class, com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.Builder.class);
    }

    public static final int RESPONSE_CODE_FIELD_NUMBER = 1;
    private int responseCode_;
    /**
     * <code>int32 response_code = 1;</code>
     */
    public int getResponseCode() {
      return responseCode_;
    }

    public static final int RESPONSE_MESSAGE_FIELD_NUMBER = 2;
    private volatile java.lang.Object responseMessage_;
    /**
     * <code>string response_message = 2;</code>
     */
    public java.lang.String getResponseMessage() {
      java.lang.Object ref = responseMessage_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        responseMessage_ = s;
        return s;
      }
    }
    /**
     * <code>string response_message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getResponseMessageBytes() {
      java.lang.Object ref = responseMessage_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        responseMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ID_FIELD_NUMBER = 3;
    private long id_;
    /**
     * <code>uint64 id = 3;</code>
     */
    public long getId() {
      return id_;
    }

    public static final int NAME_FIELD_NUMBER = 4;
    private volatile java.lang.Object name_;
    /**
     * <code>string name = 4;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 4;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AVATAR_FIELD_NUMBER = 5;
    private volatile java.lang.Object avatar_;
    /**
     * <code>string avatar = 5;</code>
     */
    public java.lang.String getAvatar() {
      java.lang.Object ref = avatar_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        avatar_ = s;
        return s;
      }
    }
    /**
     * <code>string avatar = 5;</code>
     */
    public com.google.protobuf.ByteString
        getAvatarBytes() {
      java.lang.Object ref = avatar_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        avatar_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (responseCode_ != 0) {
        output.writeInt32(1, responseCode_);
      }
      if (!getResponseMessageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, responseMessage_);
      }
      if (id_ != 0L) {
        output.writeUInt64(3, id_);
      }
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, name_);
      }
      if (!getAvatarBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, avatar_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (responseCode_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, responseCode_);
      }
      if (!getResponseMessageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, responseMessage_);
      }
      if (id_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(3, id_);
      }
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, name_);
      }
      if (!getAvatarBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, avatar_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo)) {
        return super.equals(obj);
      }
      com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo other = (com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo) obj;

      boolean result = true;
      result = result && (getResponseCode()
          == other.getResponseCode());
      result = result && getResponseMessage()
          .equals(other.getResponseMessage());
      result = result && (getId()
          == other.getId());
      result = result && getName()
          .equals(other.getName());
      result = result && getAvatar()
          .equals(other.getAvatar());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RESPONSE_CODE_FIELD_NUMBER;
      hash = (53 * hash) + getResponseCode();
      hash = (37 * hash) + RESPONSE_MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getResponseMessage().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getId());
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + AVATAR_FIELD_NUMBER;
      hash = (53 * hash) + getAvatar().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.x_keam.protobuff.model.PUserInfo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.x_keam.protobuff.model.PUserInfo)
        com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.x_keam.protobuff.model.PUserInfoOuterClass.internal_static_com_x_keam_protobuff_model_PUserInfo_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.x_keam.protobuff.model.PUserInfoOuterClass.internal_static_com_x_keam_protobuff_model_PUserInfo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.class, com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.Builder.class);
      }

      // Construct using com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        responseCode_ = 0;

        responseMessage_ = "";

        id_ = 0L;

        name_ = "";

        avatar_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.x_keam.protobuff.model.PUserInfoOuterClass.internal_static_com_x_keam_protobuff_model_PUserInfo_descriptor;
      }

      public com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo getDefaultInstanceForType() {
        return com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.getDefaultInstance();
      }

      public com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo build() {
        com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo buildPartial() {
        com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo result = new com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo(this);
        result.responseCode_ = responseCode_;
        result.responseMessage_ = responseMessage_;
        result.id_ = id_;
        result.name_ = name_;
        result.avatar_ = avatar_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo) {
          return mergeFrom((com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo other) {
        if (other == com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo.getDefaultInstance()) return this;
        if (other.getResponseCode() != 0) {
          setResponseCode(other.getResponseCode());
        }
        if (!other.getResponseMessage().isEmpty()) {
          responseMessage_ = other.responseMessage_;
          onChanged();
        }
        if (other.getId() != 0L) {
          setId(other.getId());
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (!other.getAvatar().isEmpty()) {
          avatar_ = other.avatar_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int responseCode_ ;
      /**
       * <code>int32 response_code = 1;</code>
       */
      public int getResponseCode() {
        return responseCode_;
      }
      /**
       * <code>int32 response_code = 1;</code>
       */
      public Builder setResponseCode(int value) {
        
        responseCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 response_code = 1;</code>
       */
      public Builder clearResponseCode() {
        
        responseCode_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object responseMessage_ = "";
      /**
       * <code>string response_message = 2;</code>
       */
      public java.lang.String getResponseMessage() {
        java.lang.Object ref = responseMessage_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          responseMessage_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string response_message = 2;</code>
       */
      public com.google.protobuf.ByteString
          getResponseMessageBytes() {
        java.lang.Object ref = responseMessage_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          responseMessage_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string response_message = 2;</code>
       */
      public Builder setResponseMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        responseMessage_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string response_message = 2;</code>
       */
      public Builder clearResponseMessage() {
        
        responseMessage_ = getDefaultInstance().getResponseMessage();
        onChanged();
        return this;
      }
      /**
       * <code>string response_message = 2;</code>
       */
      public Builder setResponseMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        responseMessage_ = value;
        onChanged();
        return this;
      }

      private long id_ ;
      /**
       * <code>uint64 id = 3;</code>
       */
      public long getId() {
        return id_;
      }
      /**
       * <code>uint64 id = 3;</code>
       */
      public Builder setId(long value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint64 id = 3;</code>
       */
      public Builder clearId() {
        
        id_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 4;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 4;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 4;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 4;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 4;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object avatar_ = "";
      /**
       * <code>string avatar = 5;</code>
       */
      public java.lang.String getAvatar() {
        java.lang.Object ref = avatar_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          avatar_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string avatar = 5;</code>
       */
      public com.google.protobuf.ByteString
          getAvatarBytes() {
        java.lang.Object ref = avatar_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          avatar_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string avatar = 5;</code>
       */
      public Builder setAvatar(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        avatar_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string avatar = 5;</code>
       */
      public Builder clearAvatar() {
        
        avatar_ = getDefaultInstance().getAvatar();
        onChanged();
        return this;
      }
      /**
       * <code>string avatar = 5;</code>
       */
      public Builder setAvatarBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        avatar_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:com.x_keam.protobuff.model.PUserInfo)
    }

    // @@protoc_insertion_point(class_scope:com.x_keam.protobuff.model.PUserInfo)
    private static final com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo();
    }

    public static com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PUserInfo>
        PARSER = new com.google.protobuf.AbstractParser<PUserInfo>() {
      public PUserInfo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PUserInfo(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PUserInfo> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PUserInfo> getParserForType() {
      return PARSER;
    }

    public com.x_keam.protobuff.model.PUserInfoOuterClass.PUserInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_x_keam_protobuff_model_PUserInfo_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_x_keam_protobuff_model_PUserInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021p_user_info.proto\022\032com.x_keam.protobuf" +
      "f.model\"f\n\tPUserInfo\022\025\n\rresponse_code\030\001 " +
      "\001(\005\022\030\n\020response_message\030\002 \001(\t\022\n\n\002id\030\003 \001(" +
      "\004\022\014\n\004name\030\004 \001(\t\022\016\n\006avatar\030\005 \001(\tB\034\n\032com.x" +
      "_keam.protobuff.modelb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_x_keam_protobuff_model_PUserInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_x_keam_protobuff_model_PUserInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_x_keam_protobuff_model_PUserInfo_descriptor,
        new java.lang.String[] { "ResponseCode", "ResponseMessage", "Id", "Name", "Avatar", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
