// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: Protocol_v3.proto
// </auto-generated>
#pragma warning disable 1591, 0612, 3021, 8981
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
namespace Org.Example {

  /// <summary>Holder for reflection information generated from Protocol_v3.proto</summary>
  public static partial class ProtocolV3Reflection {

    #region Descriptor
    /// <summary>File descriptor for Protocol_v3.proto</summary>
    public static pbr::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbr::FileDescriptor descriptor;

    static ProtocolV3Reflection() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          string.Concat(
            "ChFQcm90b2NvbF92My5wcm90bxILb3JnLmV4YW1wbGUiMwoRUGVyc29hbmFP",
            "ZmljaXVEdG8SDAoEbmFtZRgBIAEoCRIQCghwYXNzd29yZBgCIAEoCSK8AgoN",
            "Q2xpZW50UmVxdWVzdBItCgR0eXBlGAEgASgOMh8ub3JnLmV4YW1wbGUuQ2xp",
            "ZW50UmVxdWVzdC5UeXBlEiwKBHVzZXIYAiABKAsyHi5vcmcuZXhhbXBsZS5Q",
            "ZXJzb2FuYU9maWNpdUR0bxIaChJwZXJzb2FuYU9maWNpdU5hbWUYAyABKAkS",
            "EgoKbnVtZUVjaGlwYRgEIAEoCRIXCg9udW1lUGFydGljaXBhbnQYBSABKAkS",
            "EAoIY2FwTW90b3IYBiABKAkicwoEVHlwZRIKCgZVbmtvd24QABIJCgVMT0dJ",
            "ThABEhMKD05FV19QQVJUSUNJUEFOVBACEhoKFk5SX1BBUlRJQ0lQQU5UU19C",
            "WVJBQ0UQAxIXChNQQVJUSUNJUEFOVFNfQllURUFNEAQSCgoGTE9HT1VUEAUi",
            "jAIKDkNsaWVudFJlc3BvbnNlEi4KBHR5cGUYASABKA4yIC5vcmcuZXhhbXBs",
            "ZS5DbGllbnRSZXNwb25zZS5UeXBlEg0KBWVycm9yGAIgASgJEjoKEnBlcnNv",
            "YW5hT2ZpY2l1RHRvcxgDIAMoCzIeLm9yZy5leGFtcGxlLlBlcnNvYW5hT2Zp",
            "Y2l1RHRvEjkKEXBlcnNvYW5hT2ZpY2l1RHRvGAQgASgLMh4ub3JnLmV4YW1w",
            "bGUuUGVyc29hbmFPZmljaXVEdG8iRAoEVHlwZRILCgdVbmtub3duEAASBgoC",
            "T0sQARIJCgVFUlJPUhACEhwKGFBFUlNPQU5BT0ZJQ0lVX0xPR0dFRF9JThAD",
            "QioKHW9yZy5leGFtcGxlLnByb3RvYnVmZnByb3RvY29sQglQcm90b2J1ZnNi",
            "BnByb3RvMw=="));
      descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
          new pbr::FileDescriptor[] { },
          new pbr::GeneratedClrTypeInfo(null, null, new pbr::GeneratedClrTypeInfo[] {
            new pbr::GeneratedClrTypeInfo(typeof(global::Org.Example.PersoanaOficiuDto), global::Org.Example.PersoanaOficiuDto.Parser, new[]{ "Name", "Password" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Org.Example.ClientRequest), global::Org.Example.ClientRequest.Parser, new[]{ "Type", "User", "PersoanaOficiuName", "NumeEchipa", "NumeParticipant", "CapMotor" }, null, new[]{ typeof(global::Org.Example.ClientRequest.Types.Type) }, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Org.Example.ClientResponse), global::Org.Example.ClientResponse.Parser, new[]{ "Type", "Error", "PersoanaOficiuDtos", "PersoanaOficiuDto" }, null, new[]{ typeof(global::Org.Example.ClientResponse.Types.Type) }, null, null)
          }));
    }
    #endregion

  }
  #region Messages
  [global::System.Diagnostics.DebuggerDisplayAttribute("{ToString(),nq}")]
  public sealed partial class PersoanaOficiuDto : pb::IMessage<PersoanaOficiuDto>
  #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      , pb::IBufferMessage
  #endif
  {
    private static readonly pb::MessageParser<PersoanaOficiuDto> _parser = new pb::MessageParser<PersoanaOficiuDto>(() => new PersoanaOficiuDto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static pb::MessageParser<PersoanaOficiuDto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Org.Example.ProtocolV3Reflection.Descriptor.MessageTypes[0]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public PersoanaOficiuDto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public PersoanaOficiuDto(PersoanaOficiuDto other) : this() {
      name_ = other.name_;
      password_ = other.password_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public PersoanaOficiuDto Clone() {
      return new PersoanaOficiuDto(this);
    }

    /// <summary>Field number for the "name" field.</summary>
    public const int NameFieldNumber = 1;
    private string name_ = "";
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string Name {
      get { return name_; }
      set {
        name_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "password" field.</summary>
    public const int PasswordFieldNumber = 2;
    private string password_ = "";
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string Password {
      get { return password_; }
      set {
        password_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override bool Equals(object other) {
      return Equals(other as PersoanaOficiuDto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public bool Equals(PersoanaOficiuDto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (Name != other.Name) return false;
      if (Password != other.Password) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override int GetHashCode() {
      int hash = 1;
      if (Name.Length != 0) hash ^= Name.GetHashCode();
      if (Password.Length != 0) hash ^= Password.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void WriteTo(pb::CodedOutputStream output) {
    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      output.WriteRawMessage(this);
    #else
      if (Name.Length != 0) {
        output.WriteRawTag(10);
        output.WriteString(Name);
      }
      if (Password.Length != 0) {
        output.WriteRawTag(18);
        output.WriteString(Password);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    #endif
    }

    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    void pb::IBufferMessage.InternalWriteTo(ref pb::WriteContext output) {
      if (Name.Length != 0) {
        output.WriteRawTag(10);
        output.WriteString(Name);
      }
      if (Password.Length != 0) {
        output.WriteRawTag(18);
        output.WriteString(Password);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(ref output);
      }
    }
    #endif

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public int CalculateSize() {
      int size = 0;
      if (Name.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(Name);
      }
      if (Password.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(Password);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void MergeFrom(PersoanaOficiuDto other) {
      if (other == null) {
        return;
      }
      if (other.Name.Length != 0) {
        Name = other.Name;
      }
      if (other.Password.Length != 0) {
        Password = other.Password;
      }
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void MergeFrom(pb::CodedInputStream input) {
    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      input.ReadRawMessage(this);
    #else
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 10: {
            Name = input.ReadString();
            break;
          }
          case 18: {
            Password = input.ReadString();
            break;
          }
        }
      }
    #endif
    }

    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    void pb::IBufferMessage.InternalMergeFrom(ref pb::ParseContext input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, ref input);
            break;
          case 10: {
            Name = input.ReadString();
            break;
          }
          case 18: {
            Password = input.ReadString();
            break;
          }
        }
      }
    }
    #endif

  }

  [global::System.Diagnostics.DebuggerDisplayAttribute("{ToString(),nq}")]
  public sealed partial class ClientRequest : pb::IMessage<ClientRequest>
  #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      , pb::IBufferMessage
  #endif
  {
    private static readonly pb::MessageParser<ClientRequest> _parser = new pb::MessageParser<ClientRequest>(() => new ClientRequest());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static pb::MessageParser<ClientRequest> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Org.Example.ProtocolV3Reflection.Descriptor.MessageTypes[1]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public ClientRequest() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public ClientRequest(ClientRequest other) : this() {
      type_ = other.type_;
      user_ = other.user_ != null ? other.user_.Clone() : null;
      persoanaOficiuName_ = other.persoanaOficiuName_;
      numeEchipa_ = other.numeEchipa_;
      numeParticipant_ = other.numeParticipant_;
      capMotor_ = other.capMotor_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public ClientRequest Clone() {
      return new ClientRequest(this);
    }

    /// <summary>Field number for the "type" field.</summary>
    public const int TypeFieldNumber = 1;
    private global::Org.Example.ClientRequest.Types.Type type_ = global::Org.Example.ClientRequest.Types.Type.Unkown;
    /// <summary>
    /// Identifies which request is filled in.
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public global::Org.Example.ClientRequest.Types.Type Type {
      get { return type_; }
      set {
        type_ = value;
      }
    }

    /// <summary>Field number for the "user" field.</summary>
    public const int UserFieldNumber = 2;
    private global::Org.Example.PersoanaOficiuDto user_;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public global::Org.Example.PersoanaOficiuDto User {
      get { return user_; }
      set {
        user_ = value;
      }
    }

    /// <summary>Field number for the "persoanaOficiuName" field.</summary>
    public const int PersoanaOficiuNameFieldNumber = 3;
    private string persoanaOficiuName_ = "";
    /// <summary>
    /// pentru LOGOUT persoanaOficiu
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string PersoanaOficiuName {
      get { return persoanaOficiuName_; }
      set {
        persoanaOficiuName_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "numeEchipa" field.</summary>
    public const int NumeEchipaFieldNumber = 4;
    private string numeEchipa_ = "";
    /// <summary>
    /// pentru FILTRARE PARTICIPANTI DUPA ECHIPA
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string NumeEchipa {
      get { return numeEchipa_; }
      set {
        numeEchipa_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "numeParticipant" field.</summary>
    public const int NumeParticipantFieldNumber = 5;
    private string numeParticipant_ = "";
    /// <summary>
    /// pentru inscriere PARTICIPANT
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string NumeParticipant {
      get { return numeParticipant_; }
      set {
        numeParticipant_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "capMotor" field.</summary>
    public const int CapMotorFieldNumber = 6;
    private string capMotor_ = "";
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string CapMotor {
      get { return capMotor_; }
      set {
        capMotor_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override bool Equals(object other) {
      return Equals(other as ClientRequest);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public bool Equals(ClientRequest other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (Type != other.Type) return false;
      if (!object.Equals(User, other.User)) return false;
      if (PersoanaOficiuName != other.PersoanaOficiuName) return false;
      if (NumeEchipa != other.NumeEchipa) return false;
      if (NumeParticipant != other.NumeParticipant) return false;
      if (CapMotor != other.CapMotor) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override int GetHashCode() {
      int hash = 1;
      if (Type != global::Org.Example.ClientRequest.Types.Type.Unkown) hash ^= Type.GetHashCode();
      if (user_ != null) hash ^= User.GetHashCode();
      if (PersoanaOficiuName.Length != 0) hash ^= PersoanaOficiuName.GetHashCode();
      if (NumeEchipa.Length != 0) hash ^= NumeEchipa.GetHashCode();
      if (NumeParticipant.Length != 0) hash ^= NumeParticipant.GetHashCode();
      if (CapMotor.Length != 0) hash ^= CapMotor.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void WriteTo(pb::CodedOutputStream output) {
    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      output.WriteRawMessage(this);
    #else
      if (Type != global::Org.Example.ClientRequest.Types.Type.Unkown) {
        output.WriteRawTag(8);
        output.WriteEnum((int) Type);
      }
      if (user_ != null) {
        output.WriteRawTag(18);
        output.WriteMessage(User);
      }
      if (PersoanaOficiuName.Length != 0) {
        output.WriteRawTag(26);
        output.WriteString(PersoanaOficiuName);
      }
      if (NumeEchipa.Length != 0) {
        output.WriteRawTag(34);
        output.WriteString(NumeEchipa);
      }
      if (NumeParticipant.Length != 0) {
        output.WriteRawTag(42);
        output.WriteString(NumeParticipant);
      }
      if (CapMotor.Length != 0) {
        output.WriteRawTag(50);
        output.WriteString(CapMotor);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    #endif
    }

    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    void pb::IBufferMessage.InternalWriteTo(ref pb::WriteContext output) {
      if (Type != global::Org.Example.ClientRequest.Types.Type.Unkown) {
        output.WriteRawTag(8);
        output.WriteEnum((int) Type);
      }
      if (user_ != null) {
        output.WriteRawTag(18);
        output.WriteMessage(User);
      }
      if (PersoanaOficiuName.Length != 0) {
        output.WriteRawTag(26);
        output.WriteString(PersoanaOficiuName);
      }
      if (NumeEchipa.Length != 0) {
        output.WriteRawTag(34);
        output.WriteString(NumeEchipa);
      }
      if (NumeParticipant.Length != 0) {
        output.WriteRawTag(42);
        output.WriteString(NumeParticipant);
      }
      if (CapMotor.Length != 0) {
        output.WriteRawTag(50);
        output.WriteString(CapMotor);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(ref output);
      }
    }
    #endif

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public int CalculateSize() {
      int size = 0;
      if (Type != global::Org.Example.ClientRequest.Types.Type.Unkown) {
        size += 1 + pb::CodedOutputStream.ComputeEnumSize((int) Type);
      }
      if (user_ != null) {
        size += 1 + pb::CodedOutputStream.ComputeMessageSize(User);
      }
      if (PersoanaOficiuName.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(PersoanaOficiuName);
      }
      if (NumeEchipa.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(NumeEchipa);
      }
      if (NumeParticipant.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(NumeParticipant);
      }
      if (CapMotor.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(CapMotor);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void MergeFrom(ClientRequest other) {
      if (other == null) {
        return;
      }
      if (other.Type != global::Org.Example.ClientRequest.Types.Type.Unkown) {
        Type = other.Type;
      }
      if (other.user_ != null) {
        if (user_ == null) {
          User = new global::Org.Example.PersoanaOficiuDto();
        }
        User.MergeFrom(other.User);
      }
      if (other.PersoanaOficiuName.Length != 0) {
        PersoanaOficiuName = other.PersoanaOficiuName;
      }
      if (other.NumeEchipa.Length != 0) {
        NumeEchipa = other.NumeEchipa;
      }
      if (other.NumeParticipant.Length != 0) {
        NumeParticipant = other.NumeParticipant;
      }
      if (other.CapMotor.Length != 0) {
        CapMotor = other.CapMotor;
      }
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void MergeFrom(pb::CodedInputStream input) {
    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      input.ReadRawMessage(this);
    #else
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 8: {
            Type = (global::Org.Example.ClientRequest.Types.Type) input.ReadEnum();
            break;
          }
          case 18: {
            if (user_ == null) {
              User = new global::Org.Example.PersoanaOficiuDto();
            }
            input.ReadMessage(User);
            break;
          }
          case 26: {
            PersoanaOficiuName = input.ReadString();
            break;
          }
          case 34: {
            NumeEchipa = input.ReadString();
            break;
          }
          case 42: {
            NumeParticipant = input.ReadString();
            break;
          }
          case 50: {
            CapMotor = input.ReadString();
            break;
          }
        }
      }
    #endif
    }

    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    void pb::IBufferMessage.InternalMergeFrom(ref pb::ParseContext input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, ref input);
            break;
          case 8: {
            Type = (global::Org.Example.ClientRequest.Types.Type) input.ReadEnum();
            break;
          }
          case 18: {
            if (user_ == null) {
              User = new global::Org.Example.PersoanaOficiuDto();
            }
            input.ReadMessage(User);
            break;
          }
          case 26: {
            PersoanaOficiuName = input.ReadString();
            break;
          }
          case 34: {
            NumeEchipa = input.ReadString();
            break;
          }
          case 42: {
            NumeParticipant = input.ReadString();
            break;
          }
          case 50: {
            CapMotor = input.ReadString();
            break;
          }
        }
      }
    }
    #endif

    #region Nested types
    /// <summary>Container for nested types declared in the ClientRequest message type.</summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static partial class Types {
      public enum Type {
        [pbr::OriginalName("Unkown")] Unkown = 0,
        [pbr::OriginalName("LOGIN")] Login = 1,
        [pbr::OriginalName("NEW_PARTICIPANT")] NewParticipant = 2,
        [pbr::OriginalName("NR_PARTICIPANTS_BYRACE")] NrParticipantsByrace = 3,
        [pbr::OriginalName("PARTICIPANTS_BYTEAM")] ParticipantsByteam = 4,
        [pbr::OriginalName("LOGOUT")] Logout = 5,
      }

    }
    #endregion

  }

  [global::System.Diagnostics.DebuggerDisplayAttribute("{ToString(),nq}")]
  public sealed partial class ClientResponse : pb::IMessage<ClientResponse>
  #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      , pb::IBufferMessage
  #endif
  {
    private static readonly pb::MessageParser<ClientResponse> _parser = new pb::MessageParser<ClientResponse>(() => new ClientResponse());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static pb::MessageParser<ClientResponse> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Org.Example.ProtocolV3Reflection.Descriptor.MessageTypes[2]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public ClientResponse() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public ClientResponse(ClientResponse other) : this() {
      type_ = other.type_;
      error_ = other.error_;
      persoanaOficiuDtos_ = other.persoanaOficiuDtos_.Clone();
      persoanaOficiuDto_ = other.persoanaOficiuDto_ != null ? other.persoanaOficiuDto_.Clone() : null;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public ClientResponse Clone() {
      return new ClientResponse(this);
    }

    /// <summary>Field number for the "type" field.</summary>
    public const int TypeFieldNumber = 1;
    private global::Org.Example.ClientResponse.Types.Type type_ = global::Org.Example.ClientResponse.Types.Type.Unknown;
    /// <summary>
    /// Identifies which request is filled in.
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public global::Org.Example.ClientResponse.Types.Type Type {
      get { return type_; }
      set {
        type_ = value;
      }
    }

    /// <summary>Field number for the "error" field.</summary>
    public const int ErrorFieldNumber = 2;
    private string error_ = "";
    /// <summary>
    /// One of the following will be filled in, depending on the type.
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public string Error {
      get { return error_; }
      set {
        error_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "persoanaOficiuDtos" field.</summary>
    public const int PersoanaOficiuDtosFieldNumber = 3;
    private static readonly pb::FieldCodec<global::Org.Example.PersoanaOficiuDto> _repeated_persoanaOficiuDtos_codec
        = pb::FieldCodec.ForMessage(26, global::Org.Example.PersoanaOficiuDto.Parser);
    private readonly pbc::RepeatedField<global::Org.Example.PersoanaOficiuDto> persoanaOficiuDtos_ = new pbc::RepeatedField<global::Org.Example.PersoanaOficiuDto>();
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public pbc::RepeatedField<global::Org.Example.PersoanaOficiuDto> PersoanaOficiuDtos {
      get { return persoanaOficiuDtos_; }
    }

    /// <summary>Field number for the "persoanaOficiuDto" field.</summary>
    public const int PersoanaOficiuDtoFieldNumber = 4;
    private global::Org.Example.PersoanaOficiuDto persoanaOficiuDto_;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public global::Org.Example.PersoanaOficiuDto PersoanaOficiuDto {
      get { return persoanaOficiuDto_; }
      set {
        persoanaOficiuDto_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override bool Equals(object other) {
      return Equals(other as ClientResponse);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public bool Equals(ClientResponse other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (Type != other.Type) return false;
      if (Error != other.Error) return false;
      if(!persoanaOficiuDtos_.Equals(other.persoanaOficiuDtos_)) return false;
      if (!object.Equals(PersoanaOficiuDto, other.PersoanaOficiuDto)) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override int GetHashCode() {
      int hash = 1;
      if (Type != global::Org.Example.ClientResponse.Types.Type.Unknown) hash ^= Type.GetHashCode();
      if (Error.Length != 0) hash ^= Error.GetHashCode();
      hash ^= persoanaOficiuDtos_.GetHashCode();
      if (persoanaOficiuDto_ != null) hash ^= PersoanaOficiuDto.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void WriteTo(pb::CodedOutputStream output) {
    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      output.WriteRawMessage(this);
    #else
      if (Type != global::Org.Example.ClientResponse.Types.Type.Unknown) {
        output.WriteRawTag(8);
        output.WriteEnum((int) Type);
      }
      if (Error.Length != 0) {
        output.WriteRawTag(18);
        output.WriteString(Error);
      }
      persoanaOficiuDtos_.WriteTo(output, _repeated_persoanaOficiuDtos_codec);
      if (persoanaOficiuDto_ != null) {
        output.WriteRawTag(34);
        output.WriteMessage(PersoanaOficiuDto);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    #endif
    }

    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    void pb::IBufferMessage.InternalWriteTo(ref pb::WriteContext output) {
      if (Type != global::Org.Example.ClientResponse.Types.Type.Unknown) {
        output.WriteRawTag(8);
        output.WriteEnum((int) Type);
      }
      if (Error.Length != 0) {
        output.WriteRawTag(18);
        output.WriteString(Error);
      }
      persoanaOficiuDtos_.WriteTo(ref output, _repeated_persoanaOficiuDtos_codec);
      if (persoanaOficiuDto_ != null) {
        output.WriteRawTag(34);
        output.WriteMessage(PersoanaOficiuDto);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(ref output);
      }
    }
    #endif

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public int CalculateSize() {
      int size = 0;
      if (Type != global::Org.Example.ClientResponse.Types.Type.Unknown) {
        size += 1 + pb::CodedOutputStream.ComputeEnumSize((int) Type);
      }
      if (Error.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(Error);
      }
      size += persoanaOficiuDtos_.CalculateSize(_repeated_persoanaOficiuDtos_codec);
      if (persoanaOficiuDto_ != null) {
        size += 1 + pb::CodedOutputStream.ComputeMessageSize(PersoanaOficiuDto);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void MergeFrom(ClientResponse other) {
      if (other == null) {
        return;
      }
      if (other.Type != global::Org.Example.ClientResponse.Types.Type.Unknown) {
        Type = other.Type;
      }
      if (other.Error.Length != 0) {
        Error = other.Error;
      }
      persoanaOficiuDtos_.Add(other.persoanaOficiuDtos_);
      if (other.persoanaOficiuDto_ != null) {
        if (persoanaOficiuDto_ == null) {
          PersoanaOficiuDto = new global::Org.Example.PersoanaOficiuDto();
        }
        PersoanaOficiuDto.MergeFrom(other.PersoanaOficiuDto);
      }
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public void MergeFrom(pb::CodedInputStream input) {
    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
      input.ReadRawMessage(this);
    #else
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 8: {
            Type = (global::Org.Example.ClientResponse.Types.Type) input.ReadEnum();
            break;
          }
          case 18: {
            Error = input.ReadString();
            break;
          }
          case 26: {
            persoanaOficiuDtos_.AddEntriesFrom(input, _repeated_persoanaOficiuDtos_codec);
            break;
          }
          case 34: {
            if (persoanaOficiuDto_ == null) {
              PersoanaOficiuDto = new global::Org.Example.PersoanaOficiuDto();
            }
            input.ReadMessage(PersoanaOficiuDto);
            break;
          }
        }
      }
    #endif
    }

    #if !GOOGLE_PROTOBUF_REFSTRUCT_COMPATIBILITY_MODE
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    void pb::IBufferMessage.InternalMergeFrom(ref pb::ParseContext input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, ref input);
            break;
          case 8: {
            Type = (global::Org.Example.ClientResponse.Types.Type) input.ReadEnum();
            break;
          }
          case 18: {
            Error = input.ReadString();
            break;
          }
          case 26: {
            persoanaOficiuDtos_.AddEntriesFrom(ref input, _repeated_persoanaOficiuDtos_codec);
            break;
          }
          case 34: {
            if (persoanaOficiuDto_ == null) {
              PersoanaOficiuDto = new global::Org.Example.PersoanaOficiuDto();
            }
            input.ReadMessage(PersoanaOficiuDto);
            break;
          }
        }
      }
    }
    #endif

    #region Nested types
    /// <summary>Container for nested types declared in the ClientResponse message type.</summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    [global::System.CodeDom.Compiler.GeneratedCode("protoc", null)]
    public static partial class Types {
      public enum Type {
        [pbr::OriginalName("Unknown")] Unknown = 0,
        [pbr::OriginalName("OK")] Ok = 1,
        [pbr::OriginalName("ERROR")] Error = 2,
        [pbr::OriginalName("PERSOANAOFICIU_LOGGED_IN")] PersoanaoficiuLoggedIn = 3,
      }

    }
    #endregion

  }

  #endregion

}

#endregion Designer generated code
