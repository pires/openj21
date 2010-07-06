/*
 *  OpenJ21 Copyright (C) 2010 Paulo Pires
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 */
package net.openj21.mih.datatype;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 * Enum for the TLV data types that are used in the MIH protocol.
 */
public enum TLVType {
    /**
     * No TLV type.
     */
    NONE(0),

    /**
     * Source MIHF ID.
     */
    SOURCE_MIHF_ID(1),

    /**
     * Destination MIHF ID.
     */
    DESTINATION_MIHF_ID(2),

    /**
     * Status.
     */
    STATUS(3),

    /**
     * Link type
     */
    LINK_TYPE(4),

    /**
     * MIH event list
     */
    MIH_EVENT_LIST(5),

    /**
     * MIH command list
     */
    MIH_COMMAND_LIST(6),

    /**
     * MIIS query type list
     */
    MIIS_QUERY_TYPE_LIST(7),

    /**
     * Transport option list
     */
    TRANSPORT_OPTION_LIST(8),

    /**
     * Link address list
     */
    LINK_ADDRESS_LIST(9),

    /**
     * MBB handover support
     */
    MBB_HANDOVER_SUPPORT(10),

    /**
     * Register request code
     */
    REGISTER_REQUEST_CODE(11),

    /**
     * Valid time interval
     */
    VALID_TIME_INTERVAL(12),

    /**
     * Link identifier
     */
    LINK_IDENTIFIER(13),

    /**
     * New link identifier
     */
    NEW_LINK_IDENTIFIER(14),

    /**
     * Old access router
     */
    OLD_ACCESS_ROUTER(15),

    /**
     * New access router
     */
    NEW_ACCESS_ROUTER(16),

    /**
     * IP renewal flag
     */
    IP_RENEWAL_FLAG(17),

    /**
     * Mobility management support
     */
    MOBILITY_MANAGEMENT_SUPPORT(18),

    /**
     * IP address configuration methods
     */
    IP_ADDRESS_CONFIGURATION_METHODS(19),

    /**
     * Link down reason code
     */
    LINK_DOWN_REASON_CODE(20),

    /**
     * Time interval
     */
    TIME_INTERVAL(21),

    /**
     * Link going down reason
     */
    LINK_GOING_DOWN_REASON(22),

    /**
     * Link parameter report list
     */
    LINK_PARAMETER_REPORT_LIST(23),

    /**
     * Device states request
     */
    DEVICE_STATES_REQUEST(24),

    /**
     * Link identifier list
     */
    LINK_IDENTIFIER_LIST(25),

    /**
     * Device states response list
     */
    DEVICE_STATES_RESPONSE_LIST(26),

    /**
     * Get status request set
     */
    GET_STATUS_REQUEST_SET(27),

    /**
     * Get status response list
     */
    GET_STATUS_RESPONSE_LIST(28),

    /**
     * Configure request list
     */
    CONFIGURE_REQUEST_LIST(29),

    /**
     * Configure response list
     */
    CONFIGURE_RESPONSE_LIST(30),

    /**
     * List of link PoA list
     */
    LIST_OF_LINK_POA_LIST(31),

    /**
     * Preferred link list
     */
    PREFERRED_LINK_LIST(32),

    /**
     * Handover resource query list
     */
    HANDOVER_RESOURCE_QUERY_LIST(33),

    /**
     * Handover status
     */
    HANDOVER_STATUS(34),

    /**
     * Access router address
     */
    ACCESS_ROUTER_ADDRESS(35),

    /**
     * DHCP server address
     */
    DHCP_SERVER_ADDRESS(36),

    /**
     * FA address
     */
    FA_ADDRESS(37),

    /**
     * Link actions list
     */
    LINK_ACTIONS_LIST(38),

    /**
     * Link actions result list
     */
    LINK_ACTIONS_RESULT_LIST(39),

    /**
     * Handover result
     */
    HANDOVER_RESULT(40),

    /**
     * Resource status
     */
    RESOURCE_STATUS(41),

    /**
     * Resource retention status
     */
    RESOURCE_RETENTION_STATUS(42),

    /**
     * Info query binary data list
     */
    INFO_QUERY_BINARY_DATA_LIST(43),

    /**
     * Info query RDF data list
     */
    INFO_QUERY_RDF_DATA_LIST(44),

    /**
     * Info query RDF schema URL
     */
    INFO_QUERY_RDF_SCHEMA_URL(45),

    /**
     * Info query RDF schema list
     */
    INFO_QUERY_RDF_SCHEMA_LIST(46),

    /**
     * Max response size
     */
    MAX_RESPONSE_SIZE(47),

    /**
     * Info response binary data list
     */
    INFO_RESPONSE_BINARY_DATA_LIST(48),

    /**
     * Info response RDF data list
     */
    INFO_RESPONSE_RDF_DATA_LIST(49),

    /**
     * Info response RDF schema URL list
     */
    INFO_RESPONSE_RDF_SCHEMA_URL_LIST(50),

    /**
     * Info response RDF schema list
     */
    INFO_RESPONSE_RDF_SCHEMA_LIST(51),

    /**
     * Mobile node MIHF_ID
     */
    MOBILE_NODE_MIHF_ID(52),

    /**
     * Query resource report flag
     */
    QUERY_RESOURCE_REPORT_FLAG(53),

    /**
     * Event configuration info list
     */
    EVENT_CONFIGURATION_INFO_LIST(54),

    /**
     * Target network info
     */
    TARGET_NETWORK_INFO(55),

    /**
     * List of target network info
     */
    LIST_OF_TARGET_NETWORK_INFO(56),

    /**
     * Assigned resource set
     */
    ASSIGNED_RESOURCE_SET(57),

    /**
     * Link detected info list
     */
    LINK_DETECTED_INFO_LIST(58),

    /**
     * MN link ID
     */
    MN_LINK_ID(59),

    /**
     * PoA
     */
    POA(60),

    /**
     * Unauthenticated information request
     */
    UNAUTHENTICATED_INFORMATION_REQUEST(61),

    /**
     * Network type
     */
    NETWORK_TYPE(62),

    /**
     * Requested resource set
     */
    REQUESTED_RESOURCE_SET(63),

    /**
     * Reserved 64
     */
    RESERVED_64(64),

    /**
     * Reserved 65
     */
    RESERVED_65(65),

    /**
     * Reserved 66
     */
    RESERVED_66(66),

    /**
     * Reserved 67
     */
    RESERVED_67(67),

    /**
     * Reserved 68
     */
    RESERVED_68(68),

    /**
     * Reserved 69
     */
    RESERVED_69(69),

    /**
     * Reserved 70
     */
    RESERVED_70(70),

    /**
     * Reserved 71
     */
    RESERVED_71(71),

    /**
     * Reserved 72
     */
    RESERVED_72(72),

    /**
     * Reserved 73
     */
    RESERVED_73(73),

    /**
     * Reserved 74
     */
    RESERVED_74(74),

    /**
     * Reserved 75
     */
    RESERVED_75(75),

    /**
     * Reserved 76
     */
    RESERVED_76(76),

    /**
     * Reserved 77
     */
    RESERVED_77(77),

    /**
     * Reserved 78
     */
    RESERVED_78(78),

    /**
     * Reserved 79
     */
    RESERVED_79(79),

    /**
     * Reserved 80
     */
    RESERVED_80(80),

    /**
     * Reserved 81
     */
    RESERVED_81(81),

    /**
     * Reserved 82
     */
    RESERVED_82(82),

    /**
     * Reserved 83
     */
    RESERVED_83(83),

    /**
     * Reserved 84
     */
    RESERVED_84(84),

    /**
     * Reserved 85
     */
    RESERVED_85(85),

    /**
     * Reserved 86
     */
    RESERVED_86(86),

    /**
     * Reserved 87
     */
    RESERVED_87(87),

    /**
     * Reserved 88
     */
    RESERVED_88(88),

    /**
     * Reserved 89
     */
    RESERVED_89(89),

    /**
     * Reserved 90
     */
    RESERVED_90(90),

    /**
     * Reserved 91
     */
    RESERVED_91(91),

    /**
     * Reserved 92
     */
    RESERVED_92(92),

    /**
     * Reserved 93
     */
    RESERVED_93(93),

    /**
     * Reserved 94
     */
    RESERVED_94(94),

    /**
     * Reserved 95
     */
    RESERVED_95(95),

    /**
     * Reserved 96
     */
    RESERVED_96(96),

    /**
     * Reserved 97
     */
    RESERVED_97(97),

    /**
     * Reserved 98
     */
    RESERVED_98(98),

    /**
     * Reserved 99
     */
    RESERVED_99(99),

    /**
     * Vendor specific.
     */
    VENDOR_SPECIFIC(100),

    /**
     * Experimental 101
     */
    EXPERIMENTAL_101(101),

    /**
     * Experimental 102
     */
    EXPERIMENTAL_102(102),

    /**
     * Experimental 103
     */
    EXPERIMENTAL_103(103),

    /**
     * Experimental 104
     */
    EXPERIMENTAL_104(104),

    /**
     * Experimental 105
     */
    EXPERIMENTAL_105(105),

    /**
     * Experimental 106
     */
    EXPERIMENTAL_106(106),

    /**
     * Experimental 107
     */
    EXPERIMENTAL_107(107),

    /**
     * Experimental 108
     */
    EXPERIMENTAL_108(108),

    /**
     * Experimental 109
     */
    EXPERIMENTAL_109(109),

    /**
     * Experimental 110
     */
    EXPERIMENTAL_110(110),

    /**
     * Experimental 111
     */
    EXPERIMENTAL_111(111),

    /**
     * Experimental 112
     */
    EXPERIMENTAL_112(112),

    /**
     * Experimental 113
     */
    EXPERIMENTAL_113(113),

    /**
     * Experimental 114
     */
    EXPERIMENTAL_114(114),

    /**
     * Experimental 115
     */
    EXPERIMENTAL_115(115),

    /**
     * Experimental 116
     */
    EXPERIMENTAL_116(116),

    /**
     * Experimental 117
     */
    EXPERIMENTAL_117(117),

    /**
     * Experimental 118
     */
    EXPERIMENTAL_118(118),

    /**
     * Experimental 119
     */
    EXPERIMENTAL_119(119),

    /**
     * Experimental 120
     */
    EXPERIMENTAL_120(120),

    /**
     * Experimental 121
     */
    EXPERIMENTAL_121(121),

    /**
     * Experimental 122
     */
    EXPERIMENTAL_122(122),

    /**
     * Experimental 123
     */
    EXPERIMENTAL_123(123),

    /**
     * Experimental 124
     */
    EXPERIMENTAL_124(124),

    /**
     * Experimental 125
     */
    EXPERIMENTAL_125(125),

    /**
     * Experimental 126
     */
    EXPERIMENTAL_126(126),

    /**
     * Experimental 127
     */
    EXPERIMENTAL_127(127),

    /**
     * Experimental 128
     */
    EXPERIMENTAL_128(128),

    /**
     * Experimental 129
     */
    EXPERIMENTAL_129(129),

    /**
     * Experimental 130
     */
    EXPERIMENTAL_130(130),

    /**
     * Experimental 131
     */
    EXPERIMENTAL_131(131),

    /**
     * Experimental 132
     */
    EXPERIMENTAL_132(132),

    /**
     * Experimental 133
     */
    EXPERIMENTAL_133(133),

    /**
     * Experimental 134
     */
    EXPERIMENTAL_134(134),

    /**
     * Experimental 135
     */
    EXPERIMENTAL_135(135),

    /**
     * Experimental 136
     */
    EXPERIMENTAL_136(136),

    /**
     * Experimental 137
     */
    EXPERIMENTAL_137(137),

    /**
     * Experimental 138
     */
    EXPERIMENTAL_138(138),

    /**
     * Experimental 139
     */
    EXPERIMENTAL_139(139),

    /**
     * Experimental 140
     */
    EXPERIMENTAL_140(140),

    /**
     * Experimental 141
     */
    EXPERIMENTAL_141(141),

    /**
     * Experimental 142
     */
    EXPERIMENTAL_142(142),

    /**
     * Experimental 143
     */
    EXPERIMENTAL_143(143),

    /**
     * Experimental 144
     */
    EXPERIMENTAL_144(144),

    /**
     * Experimental 145
     */
    EXPERIMENTAL_145(145),

    /**
     * Experimental 146
     */
    EXPERIMENTAL_146(146),

    /**
     * Experimental 147
     */
    EXPERIMENTAL_147(147),

    /**
     * Experimental 148
     */
    EXPERIMENTAL_148(148),

    /**
     * Experimental 149
     */
    EXPERIMENTAL_149(149),

    /**
     * Experimental 150
     */
    EXPERIMENTAL_150(150),

    /**
     * Experimental 151
     */
    EXPERIMENTAL_151(151),

    /**
     * Experimental 152
     */
    EXPERIMENTAL_152(152),

    /**
     * Experimental 153
     */
    EXPERIMENTAL_153(153),

    /**
     * Experimental 154
     */
    EXPERIMENTAL_154(154),

    /**
     * Experimental 155
     */
    EXPERIMENTAL_155(155),

    /**
     * Experimental 156
     */
    EXPERIMENTAL_156(156),

    /**
     * Experimental 157
     */
    EXPERIMENTAL_157(157),

    /**
     * Experimental 158
     */
    EXPERIMENTAL_158(158),

    /**
     * Experimental 159
     */
    EXPERIMENTAL_159(159),

    /**
     * Experimental 160
     */
    EXPERIMENTAL_160(160),

    /**
     * Experimental 161
     */
    EXPERIMENTAL_161(161),

    /**
     * Experimental 162
     */
    EXPERIMENTAL_162(162),

    /**
     * Experimental 163
     */
    EXPERIMENTAL_163(163),

    /**
     * Experimental 164
     */
    EXPERIMENTAL_164(164),

    /**
     * Experimental 165
     */
    EXPERIMENTAL_165(165),

    /**
     * Experimental 166
     */
    EXPERIMENTAL_166(166),

    /**
     * Experimental 167
     */
    EXPERIMENTAL_167(167),

    /**
     * Experimental 168
     */
    EXPERIMENTAL_168(168),

    /**
     * Experimental 169
     */
    EXPERIMENTAL_169(169),

    /**
     * Experimental 170
     */
    EXPERIMENTAL_170(170),

    /**
     * Experimental 171
     */
    EXPERIMENTAL_171(171),

    /**
     * Experimental 172
     */
    EXPERIMENTAL_172(172),

    /**
     * Experimental 173
     */
    EXPERIMENTAL_173(173),

    /**
     * Experimental 174
     */
    EXPERIMENTAL_174(174),

    /**
     * Experimental 175
     */
    EXPERIMENTAL_175(175),

    /**
     * Experimental 176
     */
    EXPERIMENTAL_176(176),

    /**
     * Experimental 177
     */
    EXPERIMENTAL_177(177),

    /**
     * Experimental 178
     */
    EXPERIMENTAL_178(178),

    /**
     * Experimental 179
     */
    EXPERIMENTAL_179(179),

    /**
     * Experimental 180
     */
    EXPERIMENTAL_180(180),

    /**
     * Experimental 181
     */
    EXPERIMENTAL_181(181),

    /**
     * Experimental 182
     */
    EXPERIMENTAL_182(182),

    /**
     * Experimental 183
     */
    EXPERIMENTAL_183(183),

    /**
     * Experimental 184
     */
    EXPERIMENTAL_184(184),

    /**
     * Experimental 185
     */
    EXPERIMENTAL_185(185),

    /**
     * Experimental 186
     */
    EXPERIMENTAL_186(186),

    /**
     * Experimental 187
     */
    EXPERIMENTAL_187(187),

    /**
     * Experimental 188
     */
    EXPERIMENTAL_188(188),

    /**
     * Experimental 189
     */
    EXPERIMENTAL_189(189),

    /**
     * Experimental 190
     */
    EXPERIMENTAL_190(190),

    /**
     * Experimental 191
     */
    EXPERIMENTAL_191(191),

    /**
     * Experimental 192
     */
    EXPERIMENTAL_192(192),

    /**
     * Experimental 193
     */
    EXPERIMENTAL_193(193),

    /**
     * Experimental 194
     */
    EXPERIMENTAL_194(194),

    /**
     * Experimental 195
     */
    EXPERIMENTAL_195(195),

    /**
     * Experimental 196
     */
    EXPERIMENTAL_196(196),

    /**
     * Experimental 197
     */
    EXPERIMENTAL_197(197),

    /**
     * Experimental 198
     */
    EXPERIMENTAL_198(198),

    /**
     * Experimental 199
     */
    EXPERIMENTAL_199(199),

    /**
     * Experimental 200
     */
    EXPERIMENTAL_200(200),

    /**
     * Experimental 201
     */
    EXPERIMENTAL_201(201),

    /**
     * Experimental 202
     */
    EXPERIMENTAL_202(202),

    /**
     * Experimental 203
     */
    EXPERIMENTAL_203(203),

    /**
     * Experimental 204
     */
    EXPERIMENTAL_204(204),

    /**
     * Experimental 205
     */
    EXPERIMENTAL_205(205),

    /**
     * Experimental 206
     */
    EXPERIMENTAL_206(206),

    /**
     * Experimental 207
     */
    EXPERIMENTAL_207(207),

    /**
     * Experimental 208
     */
    EXPERIMENTAL_208(208),

    /**
     * Experimental 209
     */
    EXPERIMENTAL_209(209),

    /**
     * Experimental 210
     */
    EXPERIMENTAL_210(210),

    /**
     * Experimental 211
     */
    EXPERIMENTAL_211(211),

    /**
     * Experimental 212
     */
    EXPERIMENTAL_212(212),

    /**
     * Experimental 213
     */
    EXPERIMENTAL_213(213),

    /**
     * Experimental 214
     */
    EXPERIMENTAL_214(214),

    /**
     * Experimental 215
     */
    EXPERIMENTAL_215(215),

    /**
     * Experimental 216
     */
    EXPERIMENTAL_216(216),

    /**
     * Experimental 217
     */
    EXPERIMENTAL_217(217),

    /**
     * Experimental 218
     */
    EXPERIMENTAL_218(218),

    /**
     * Experimental 219
     */
    EXPERIMENTAL_219(219),

    /**
     * Experimental 220
     */
    EXPERIMENTAL_220(220),

    /**
     * Experimental 221
     */
    EXPERIMENTAL_221(221),

    /**
     * Experimental 222
     */
    EXPERIMENTAL_222(222),

    /**
     * Experimental 223
     */
    EXPERIMENTAL_223(223),

    /**
     * Experimental 224
     */
    EXPERIMENTAL_224(224),

    /**
     * Experimental 225
     */
    EXPERIMENTAL_225(225),

    /**
     * Experimental 226
     */
    EXPERIMENTAL_226(226),

    /**
     * Experimental 227
     */
    EXPERIMENTAL_227(227),

    /**
     * Experimental 228
     */
    EXPERIMENTAL_228(228),

    /**
     * Experimental 229
     */
    EXPERIMENTAL_229(229),

    /**
     * Experimental 230
     */
    EXPERIMENTAL_230(230),

    /**
     * Experimental 231
     */
    EXPERIMENTAL_231(231),

    /**
     * Experimental 232
     */
    EXPERIMENTAL_232(232),

    /**
     * Experimental 233
     */
    EXPERIMENTAL_233(233),

    /**
     * Experimental 234
     */
    EXPERIMENTAL_234(234),

    /**
     * Experimental 235
     */
    EXPERIMENTAL_235(235),

    /**
     * Experimental 236
     */
    EXPERIMENTAL_236(236),

    /**
     * Experimental 237
     */
    EXPERIMENTAL_237(237),

    /**
     * Experimental 238
     */
    EXPERIMENTAL_238(238),

    /**
     * Experimental 239
     */
    EXPERIMENTAL_239(239),

    /**
     * Experimental 240
     */
    EXPERIMENTAL_240(240),

    /**
     * Experimental 241
     */
    EXPERIMENTAL_241(241),

    /**
     * Experimental 242
     */
    EXPERIMENTAL_242(242),

    /**
     * Experimental 243
     */
    EXPERIMENTAL_243(243),

    /**
     * Experimental 244
     */
    EXPERIMENTAL_244(244),

    /**
     * Experimental 245
     */
    EXPERIMENTAL_245(245),

    /**
     * Experimental 246
     */
    EXPERIMENTAL_246(246),

    /**
     * Experimental 247
     */
    EXPERIMENTAL_247(247),

    /**
     * Experimental 248
     */
    EXPERIMENTAL_248(248),

    /**
     * Experimental 249
     */
    EXPERIMENTAL_249(249),

    /**
     * Experimental 250
     */
    EXPERIMENTAL_250(250),

    /**
     * Experimental 251
     */
    EXPERIMENTAL_251(251),

    /**
     * Experimental 252
     */
    EXPERIMENTAL_252(252),

    /**
     * Experimental 253
     */
    EXPERIMENTAL_253(253),

    /**
     * Experimental 254
     */
    EXPERIMENTAL_254(254),

    /**
     * Experimental 255
     */
    EXPERIMENTAL_255(255);

    /**
     * The value to instance map, used to implement @{link valueOf}.
     */
    private static final Map<Integer, TLVType> INSTANCES_BY_VALUE = unmodifiableMap(new HashMap<Integer, TLVType>(TLVType.values().length) {{
        // map all the instances
        for (TLVType instance : TLVType.values()) {
            put(instance.value, instance);
        }
    }});

    /**
     * Returns the TLVType instance that has the given value.
     *
     * @param value the value
     * @return a TLVType
     * @throws IllegalArgumentException if there is no enum const with the given value
     * @see #value()
     */
    public static TLVType valueOf(int value) throws IllegalArgumentException {
        if (INSTANCES_BY_VALUE.containsKey(value)) {
            return INSTANCES_BY_VALUE.get(value);
        }

        throw new IllegalArgumentException("No enum const TLVType with value: " + value);
    }

    /**
     * The TLV type value.
     */
    private final int value;

    /**
     * Creates a new TLVType having the given value, which should be decoded using the given data type.
     *
     * @param value the TLV type value
     */
    private TLVType(int value) {
        if (value < 0 || value > 255) throw new IllegalArgumentException("Value is out of range: " + value);
        this.value = value;
    }

    /**
     * Returns the byte value that identifies this TLVType when serialised (ranging from 0..255).
     *
     * @return the value that identifies this TLVType
     */
    public int value() {
        return value;
    }

    /**
     * Returns the value that identifies this TLVType when serialised (ranging from 0..255) as an unsigned byte.
     *
     * @return the value that identifies this TLVType, as an unsigned byte
     */
    public byte byteValue() {
        return (byte) (value & 0xFF);
    }
}
