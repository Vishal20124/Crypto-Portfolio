package com.bridgelabz.service;

import com.bridgelabz.dto.MarketDataDTO;
import java.util.List;

public interface CryptoService {
    List<MarketDataDTO> getTopMarketData();
}
