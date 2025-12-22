@Override
public List<RiskScore> getAllScores() {
    return repository.findAll();
}
