CryptoHolding holding = holdingRepo.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Holding not found with ID: " + id));
